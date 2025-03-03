package drexel.se311.kwicserver;
import java.util.ArrayList;
import java.util.TreeMap;

public class Searcher {

    public Searcher(){

    }
    
    public ArrayList<String> search(LineStorage lineStorage, String wordToFind) {

        TreeMap<Integer, String> sortedLines = new TreeMap<>();

        for (int i = 0; i < lineStorage.getShiftedLinesLength(); i++) {
            String firstWord = lineStorage.getShiftedLine(i).split(" ")[0];

            if (firstWord.equalsIgnoreCase(wordToFind)) {
                int originalIndex = lineStorage.getOriginalIndexOfShiftedLine(i);
                if (!sortedLines.containsKey(originalIndex)) {

                    String[] words = lineStorage.getOriginalLine(originalIndex).split(" ");
                    String lineToAdd = "";

                    for (String word : words) {
                        if(word.equalsIgnoreCase(wordToFind)){
                            lineToAdd += ("[" + word + "]" + " ");
                        }
                        else{
                            lineToAdd += (word + " ");
                        }
                    }

                    sortedLines.put(originalIndex, lineToAdd);
                }
            }
        }

        ArrayList<String> foundLines = new ArrayList<>(sortedLines.values());

        return foundLines;
    
    }

}
