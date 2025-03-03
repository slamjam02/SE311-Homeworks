package drexel.se311.kwicserver;
import java.util.*;

public class CircularShifter {
    public CircularShifter(){}

    public void circularShift(LineStorage lineStorage){

        for(int i = 0; i < lineStorage.getOriginalLinesLength(); i++){

            ArrayList<String> words = new ArrayList<String>(Arrays.asList(lineStorage.getOriginalLine(i).split(" ")));

            for(int shift = 0; shift < words.size(); shift++){

                String tempString = "";
                for(String word: words){
                    tempString += word + " ";
                }

                lineStorage.addShiftedLine(tempString, i);
                
                words.add(words.getFirst());
                words.removeFirst();
            }
        }
    }

}
