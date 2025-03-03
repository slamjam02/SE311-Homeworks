import java.util.ArrayList;

public class KeywordSearcher {

    public static ArrayList<Pair> searchInArrayList(Output output, ArrayList<Pair> sortedCircularShiftedArrayList, String wordToFind) {
        ArrayList<Pair> pairArrayList = new ArrayList<Pair>();
        
        for(Pair pair: sortedCircularShiftedArrayList){
            if(pair.getFirstWord().equals(wordToFind)){
                pairArrayList.add(pair);
            }
        }

        ArrayList<Pair> tempPairArrayList = new ArrayList<Pair>();

        
        for (Pair pair : pairArrayList) {
            boolean allowAdd = true;
            for(Pair pair2 : tempPairArrayList){
                if(pair.equals(pair2)){
                    allowAdd = false;
                }
            }
            if(allowAdd){
                tempPairArrayList.add(pair);
            }
        }

        return tempPairArrayList;
    
    }

}
