import java.io.*;
import java.util.ArrayList;

public class MainDriver{

    public static void main(String[] args) throws Exception {

        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        FileManager fileManager;
        CircularShifter circularShifter;

        System.out.println();
        System.out.println("~~ Welcome to the Key Word in Context (KWIC) search program! ~~");

        String path = PathChecker.getValidPath(input);

        if(path.endsWith(".txt")){
            fileManager = new TextFileManager();
        }
        else{
            System.err.println("An error occured opening the file.");
            return;
        }

        String[] stringArray = fileManager.convertFileToStringArray(path);
    
        ArrayList<Pair> circularShiftedArrayList = CircularShifter.getCircularShiftedArrayList(stringArray);
        
        PairArrayListSorter alphabeticalPairArrayListSorter = new AlphabeticalPairArrayListSorter();
        
        // Currently case sensitive
        ArrayList<Pair> sortedCircularShiftedArrayList = alphabeticalPairArrayListSorter.sort(circularShiftedArrayList);

        System.out.println("File successfully opened.\n\nWhich word would you like to find?");
        String wordToFind = input.getInput();


        ArrayList<Pair> foundArrayList = KeywordSearcher.searchInArrayList(output, sortedCircularShiftedArrayList, wordToFind);
        
        
        if (foundArrayList == null){
            output.produceOutput("\n" + wordToFind + " not found in the file provided.");
        }
        else{
            output.produceOutput("\n" + foundArrayList.size() + " sentence(s) found.");
            output.produceOutput("\n");


            for(Pair pair: foundArrayList){
                String outputLine = stringArray[pair.index];
                String[] words = outputLine.split(" ");

                for (String word : words) {
                    if(word.equals(wordToFind)){
                        output.produceOutput("\033[0;1m" + word + "\033[0;0m" + " ");
                    }
                    else{
                        output.produceOutput(word + " ");
                    }
                }

                System.out.println();
            }

        }

        output.completeOutput();
        System.out.println();
    
    }
}