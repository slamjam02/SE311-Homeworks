import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainDriver{

    protected static final int INDEX_WIDTH = 10;
    protected static final int SHIFTED_TEXT_WIDTH = 40;
    protected static final int ORIGINAL_INDEX_WIDTH = 10;

    static String inputPath;
    static String outputPath;

    static Input input;
    static Output output;

    static LineStorage lineStorage;

    static CircularShifter circularShifter = new CircularShifter();
    static Sorter sorter;
    static Searcher searcher = new Searcher();
    static Scanner scanner = new Scanner(System.in);
    static Indexer indexer = new Indexer();
    
    public static void applyConfiguration() throws Exception {        
        // Read options file
		OptionReader.readOptions();

        // Set path strings
        inputPath = OptionReader.getString("InputFileName");
        outputPath = OptionReader.getString("OutputFileName");

        // Set input strategy
		String inputObjStr = OptionReader.getString("Input"); 
        input = (Input) OptionReader.getObjectFromKey(inputObjStr);
        if(input == null){
            throw new Exception("Error getting input object type. Check config file.");
        }
		

        // Set output strategy
		String outputObjStr = OptionReader.getString("Output"); 
        output = (Output) OptionReader.getObjectFromKey(outputObjStr);
        if(output == null){
            throw new Exception("Error getting output object type. Check config file.");
        }
        if(outputObjStr.equals("TxtOutputObj")){
            ((TxtFileOutput) output).createFile(outputPath);
        }

        // Set ordering strategy
        String orderString = OptionReader.getString("Order");
        if(orderString.equals("Ascending")){
            sorter = new AscendingAlphabeticalSorter();
        } else if(orderString.equals("Descending")){
            sorter = new DescendingAlphabeticalSorter();
        }
        else {
            throw new Exception("Error setting order type. Check config file.");
        }

        // Set word filters
        String wordFilters = OptionReader.getString("TrivialWords"); 
        wordFilters = wordFilters.replaceAll("”", "");
    
        String[] wordFilterArray = wordFilters.split(",");
        indexer.setWordsToFilter(wordFilterArray);
        System.out.println("Filtered words for index generation: " + wordFilters);

        String wordFilteringOn = OptionReader.getString("WordFiltering");
        if(wordFilteringOn.equalsIgnoreCase("yes")){
            indexer.enableFiltering();
            
        } else if(wordFilteringOn.equalsIgnoreCase("no")) {
            indexer.disableFiltering();
        } else {
            throw new Exception("WordFiltering option is not \"Yes\" or \"No\"... Check config file.");
        }
        System.out.println("Filtering enabled: " + indexer.isFilteringEnabled());

        // Test that path works
        File file = new File(inputPath);
        System.out.println("File exists: " + file.exists());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        
    }

    public static void main(String[] args) throws Exception {

        System.out.println("\n\n~~ Welcome to the Key Word in Context (KWIC) search program! ~~\n");

        // Get settings from config.properties file
        applyConfiguration();

        // Generate lines from file and store
        lineStorage = new LineStorage(input.convertFileToStringArray(inputPath));
        // Circular shift
        circularShifter.circularShift(lineStorage);

        sorter.sort(lineStorage);

        // Print application options
        String prompt = """
                        \nWhat would you like to do? Please enter a number and press Enter.
                        1. KWIC Processing
                        2. Keyword Search
                        3. Index generation""";
        System.out.println(prompt);

        // Get user input for option
        int chosenOption;
        while(true){
            try {
                chosenOption = scanner.nextInt();
                if(chosenOption > 0 && chosenOption < 4){
                    scanner.nextLine();
                    break;
                }
                else {
                    System.err.println("That is not one of the provided options. Try again.");
                }
            } catch(Exception e) {
                System.err.println("That is not an integer. Try again.");
                scanner.nextLine();
            }
        }

    
        // 1. KWIC Processing
        if(chosenOption == 1){

             // Add header with formatting
            String formattedString = String.format("%-" + INDEX_WIDTH + "s %-"+ SHIFTED_TEXT_WIDTH +"s %-"+ ORIGINAL_INDEX_WIDTH +"s%n", 
                    "Index", "CircularShifted Lines", "Original Line Index").replace("\n", "");
            output.addLineToOutput(formattedString);

            output.addLineToOutput("------------------------------------------------------------------");

            // Add each line with formatting
            for (int i = 0; i < lineStorage.getShiftedLinesLength(); i++) {
                formattedString = String.format("%-" + INDEX_WIDTH + "d %-"+ SHIFTED_TEXT_WIDTH +"s %-"+ ORIGINAL_INDEX_WIDTH +"d%n", 
                (i + 1), lineStorage.getShiftedLine(i), lineStorage.getOriginalIndexOfShiftedLine(i) + 1).replace("\n", "");
                output.addLineToOutput(formattedString);
            }

            output.complete();
        }

        // 2. Keyword Search
        else if(chosenOption == 2){
            System.out.println("\nWhich word would you like to find?");
            String wordToFind = scanner.nextLine();

            ArrayList<String> foundLines = searcher.search(lineStorage, wordToFind);

            if(foundLines.isEmpty()){
                output.addLineToOutput(wordToFind + " not found.");
            } else {
                output.addLineToOutput(foundLines.size() + " sentence(s) found.");
                output.addLineToOutput("");
            }

            foundLines.forEach(output::addLineToOutput);

            output.complete();
        } 

        // 3. Index Generation
        else {
            indexer.index(lineStorage).forEach(output::addLineToOutput);
            output.complete();
        }
            

        System.out.println("Application completed successfully. Exiting.");
    }
}