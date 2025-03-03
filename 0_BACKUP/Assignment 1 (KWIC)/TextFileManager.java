import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileManager extends FileManager {

    BufferedReader bufferedReader;

    public TextFileManager(){
        super();
    }

    @Override
    protected boolean openFile(String path) throws IOException {
       
        try{
            bufferedReader = new BufferedReader(
            new FileReader(path));
            return true;
        }
       
        catch (IOException e) {
            return false;
        }
    
    }

    @Override
    public String[] convertFileToStringArray(String path) throws IOException {

        this.openFile(path);
        ArrayList<String> array = new ArrayList<String>();

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                array.add(line);
            }

            return array.toArray(new String[0]);
       
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return null;
    }

}
