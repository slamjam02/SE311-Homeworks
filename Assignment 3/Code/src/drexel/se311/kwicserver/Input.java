package drexel.se311.kwicserver;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Input {

    protected String path;

    public Input(){

    }

    public Input(String path){
        this.path = path;
    }


    public abstract boolean openFile(String path) throws IOException;

    public abstract ArrayList<String> convertFileToStringArray(String path) throws IOException;


}
