import java.util.*;

public class CircularShifter {
    public CircularShifter(){}

    public static ArrayList<Pair> getCircularShiftedArrayList(String[] array){
        ArrayList<Pair> finalArray = new ArrayList<Pair>();

        for(int i = 0; i < array.length; i++){


            ArrayList<String> words = new ArrayList<String>(Arrays.asList(array[i].split(" ")));

            for(int shift = 0; shift < words.size(); shift++){

                String tempString = "";
                for(String word: words){
                    tempString += word + " ";
                }

                finalArray.add(new Pair(tempString, i));
                
                words.add(words.getFirst());
                words.removeFirst();
            }
        }

        return finalArray;
    }

}
