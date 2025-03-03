package drexel.se311.kwicserver;
import java.util.ArrayList;

public class Indexer {
    private ArrayList<String> wordsToFilter;
    private boolean filteringEnabled;

    public Indexer(){
        wordsToFilter = new ArrayList<>();
    }

    public ArrayList<String> index(LineStorage lineStorage){
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < lineStorage.getShiftedLinesLength(); i++) {
            String word = lineStorage.getShiftedLine(i).split(" ")[0];
            if(!(wordsToFilter.contains(word) && filteringEnabled)){
                output.add(word + ", " + lineStorage.getOriginalIndexOfShiftedLine(i));
            }
        }

        return output;
    }

    public void setWordsToFilter(String[] wordFilterArray) {
        for(String elem: wordFilterArray){
            wordsToFilter.add(elem);
        }
    }

    public void enableFiltering() {
        this.filteringEnabled = true;
    }

    public void disableFiltering() {
        this.filteringEnabled = false;
    }
    
    public boolean isFilteringEnabled(){
        return this.filteringEnabled;
    }
}
