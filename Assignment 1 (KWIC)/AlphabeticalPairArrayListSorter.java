import java.util.*;

public class AlphabeticalPairArrayListSorter extends PairArrayListSorter {

    public AlphabeticalPairArrayListSorter(){

    }

    public ArrayList<Pair> sort (ArrayList<Pair> arrayList){
        arrayList.sort(Comparator.comparing(pair -> pair.getFirstLetter()));
        return arrayList;
    }
}
