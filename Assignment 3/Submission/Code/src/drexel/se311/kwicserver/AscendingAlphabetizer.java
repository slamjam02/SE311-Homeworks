package drexel.se311.kwicserver;
import java.util.*;

public class AscendingAlphabetizer extends Alphabetizer {

    private class Pair {
        String text;
        int index;
    
        private Pair(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    public AscendingAlphabetizer(){

    }


    @Override
    public void sort(LineStorage lineStorage) {
        ArrayList<Pair> list = new ArrayList<>();

        for(int i = 0; i < lineStorage.getShiftedLinesLength(); i++){
            Pair pair = new Pair(lineStorage.getShiftedLine(i), lineStorage.getOriginalIndexOfShiftedLine(i));
            list.add(pair);
        }

        list.sort((p1, p2) -> p1.text.compareTo(p2.text));
        lineStorage.removeAllShiftedLines();
        for(Pair pair: list){
            lineStorage.addShiftedLine(pair.text, pair.index);
        }
    }
}