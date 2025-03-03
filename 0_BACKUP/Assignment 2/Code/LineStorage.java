import java.util.ArrayList;

public class LineStorage {

    private class Pair {
        String text;
        int index;
    
        private Pair(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    private ArrayList<String> originalLines;
    private ArrayList<Pair> shiftedList;

    public LineStorage(ArrayList<String> originalLines){
        this.originalLines = originalLines;
        this.shiftedList = new ArrayList<Pair>();
    }

    // Original line methods

    public String getOriginalLine(int index) {
        return originalLines.get(index);
    }

    public int getOriginalLinesLength() {
        return originalLines.size();
    }


    // Shifted line methods

    public void addShiftedLine(String shiftedLine, int index) {
        shiftedList.add(new Pair(shiftedLine, index));
    }

    public void removeAllShiftedLines(){
        this.shiftedList = new ArrayList<Pair>();
    }

    public String getShiftedLine(int index) {
        return shiftedList.get(index).text;
    }

    public int getOriginalIndexOfShiftedLine(int shiftedIndex){
        return shiftedList.get(shiftedIndex).index;
    }

    public ArrayList<String> getShiftedLinesList() {
        ArrayList<String> outputList = new ArrayList<>();
        for(Pair sp: shiftedList){
            outputList.add(sp.text);
        }
        return outputList;
    }

    public int getShiftedLinesLength(){
        return this.shiftedList.size();
    }

    public String getShiftedLineAndIndex(int shiftedIndex, int circularShiftedSpacing, int originalIndexSpacing){
        return String.format("s %-" + circularShiftedSpacing + "s %-" + originalIndexSpacing + "d%n", this.shiftedList.get(shiftedIndex).text, this.shiftedList.get(shiftedIndex).index + 1);
    }

}
