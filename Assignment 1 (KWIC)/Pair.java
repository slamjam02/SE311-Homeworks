
public class Pair {
    public String string;
    public int index;
    public Pair(String string, int index){
        this.string = string;
        this.index = index;
    }

    public Character getFirstLetter(){
        return (Character) string.charAt(0);
    }

    public String getFirstWord(){
        return string.split(" ")[0];
    }

    public boolean equals(Pair otherPair) {
        return this.index == otherPair.index;
    }
}
