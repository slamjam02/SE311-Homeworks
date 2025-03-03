package drexel.se311.kwicserver;
import java.util.ArrayList;
import java.util.Comparator;

public class DescendingAlphabetizer extends Alphabetizer {


    private class Pair {
        String text;
        int index;
    
        private Pair(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    public DescendingAlphabetizer(){

    }

    public class DescendingCaseInsensitiveComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareToIgnoreCase(s1); 
        }
    }
    
    @Override
    public void sort(LineStorage lineStorage) {
        ArrayList<Pair> list = new ArrayList<>();

        for(int i = 0; i < lineStorage.getShiftedLinesLength(); i++){
            Pair pair = new Pair(lineStorage.getShiftedLine(i), lineStorage.getOriginalIndexOfShiftedLine(i));
            list.add(pair);
        }

        list.sort((p1, p2) -> p2.text.compareTo(p1.text));
        lineStorage.removeAllShiftedLines();
        for(Pair pair: list){
            lineStorage.addShiftedLine(pair.text, pair.index);
        }
    }
}