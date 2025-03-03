import java.util.ArrayList;

public abstract class Output {
    
    protected ArrayList<String> outputLines;

    public Output(){
        this.outputLines = new ArrayList<String>();
    }

    public abstract void addLineToOutput(String string);
    public abstract void complete();
}
