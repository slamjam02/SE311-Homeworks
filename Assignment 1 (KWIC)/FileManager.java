import java.io.*;

public abstract class FileManager {

    public FileManager(){}

    protected abstract boolean openFile(String path) throws IOException;
            
    public abstract String[] convertFileToStringArray(String path) throws IOException;
}
