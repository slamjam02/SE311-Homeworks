package drexel.se311.kwicserver;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KWICServer{

    protected static final int INDEX_WIDTH = 10;
    protected static final int SHIFTED_TEXT_WIDTH = 40;
    protected static final int ORIGINAL_INDEX_WIDTH = 10;

    private static int port;

    static String propertiesPath = "config.properties";

    static String inputPath;
    static String outputPath;

    static String logFilePath;

    static Input input;
    static Output output;

    static LineStorage lineStorage;

    static CircularShifter circularShifter = new CircularShifter();
    static Alphabetizer alphabetizer;
    static Searcher searcher = new Searcher();
    static Scanner scanner = new Scanner(System.in);
    static Indexer indexer = new Indexer();
    
    public static void applyConfiguration() throws Exception {        
        // Read options file
		OptionReader.readOptions(propertiesPath);

        // Set path strings
        inputPath = OptionReader.getString("InputFileName");
        outputPath = OptionReader.getString("OutputFileName");
        logFilePath = OptionReader.getString("LogFileName");

        // Set port
        port = Integer.parseInt(OptionReader.getString("Port"));

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
            ((TxtOutput) output).createFile(outputPath);
        }

        // Set ordering strategy
        String orderString = OptionReader.getString("Order");
        if(orderString.equals("Ascending")){
            alphabetizer = new AscendingAlphabetizer();
        } else if(orderString.equals("Descending")){
            alphabetizer = new DescendingAlphabetizer();
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
        System.out.println("Input file exists: " + file.exists());
        System.out.println("Absolute path of input file: " + file.getAbsolutePath());
        
    }

    public static void main(String[] args) throws Exception {

        System.out.println("\n\n~~ Welcome to the KWIC Server! ~~\n");

        // Parse arguments
        if (args.length < 1 || args.length > 3) {
            System.err.println("Arguments not formatted properly.");
            System.out.println("Format: kwic-server.jar <keyword-server | index-generation | keyword-search | kwic-processing> <keyword (keyword-search only)> <properties file path>");
            return;
        } else {
            if (args[args.length - 1].endsWith("properties")){
                propertiesPath = args[args.length - 1];
            } else if (args[0].equalsIgnoreCase("keyword-search") && args[args.length - 1].endsWith("properties") && args.length < 3){
                System.err.println("Arguments for keyword search not formatted properly.");
                System.out.println("Format: kwic-server.jar <keyword-server | index-generation | keyword-search | kwic-processing> <keyword (keyword-search only)> <properties file path>");
                return;
            }
        }


        // Get settings from config.properties file
        try {
            applyConfiguration();
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        // Generate lines from file and store
        lineStorage = new LineStorage(input.convertFileToStringArray(inputPath));

        // Circular shift
        circularShifter.circularShift(lineStorage);

        //Sort
        alphabetizer.sort(lineStorage);

        
        switch (args[0]) {
            case "keyword-server":
                System.out.println("\nListening for connections on port " + port + "...\n");
                try {
                    while (true) {
                        ServerSocket serverSocket = new ServerSocket(port);
                        Socket client = serverSocket.accept();
        
                        RequestHandler requestHandler = new RequestHandler(client);

                        Logger logger = new Logger("log.txt");


                        String log = requestHandler.runKeywordSearch(searcher, lineStorage);
                        logger.write(log);
        
                        client.close();
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Socket establishment failed: \n" + e.getMessage());
                }
                break;
        
            case "kwic-processing":
                // Add header with formatting
                output.addLineToOutput(String.format("%-" + INDEX_WIDTH + "s %-"+ SHIFTED_TEXT_WIDTH +"s %-"+ ORIGINAL_INDEX_WIDTH +"s",
                        "Index", "CircularShifted Lines", "Original Line Index"));
                output.addLineToOutput("------------------------------------------------------------------");
        
                // Add each line with formatting
                for (int i = 0; i < lineStorage.getShiftedLinesLength(); i++) {
                    String formattedString = String.format("%-" + INDEX_WIDTH + "d %-"+ SHIFTED_TEXT_WIDTH +"s %-"+ ORIGINAL_INDEX_WIDTH +"d",
                            (i + 1), lineStorage.getShiftedLine(i), lineStorage.getOriginalIndexOfShiftedLine(i) + 1);
                    output.addLineToOutput(formattedString);
                }
        
                output.complete();
                break;
        
            case "keyword-search":
                if (args.length < 2) {
                    System.out.println("Error: Missing keyword for search.");
                    return;
                }
                
                String wordToFind = args[1];
                System.out.println("\nSearching for keyword: " + wordToFind);
        
                ArrayList<String> foundLines = searcher.search(lineStorage, wordToFind);
        
                if (foundLines.isEmpty()) {
                    output.addLineToOutput(wordToFind + " not found.");
                } else {
                    output.addLineToOutput(foundLines.size() + " sentence(s) found.");
                    output.addLineToOutput("");
                    foundLines.forEach(output::addLineToOutput);
                }
        
                output.complete();
                break;
        
            case "index-generation":
                indexer.index(lineStorage).forEach(output::addLineToOutput);
                output.complete();
                break;
        
            default:
                System.err.println("The first argument provided is not supported. Try again.");
                System.out.println("Format: kwic-server.jar <keyword-server | index-generation | keyword-search | kwic-processing> <properties file path>");
                return;
        }
        

        System.out.println("Application completed successfully. Exiting.");
        return;
    }
}