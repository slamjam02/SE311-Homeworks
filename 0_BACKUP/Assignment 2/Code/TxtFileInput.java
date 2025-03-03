import java.io.*;
import java.util.ArrayList;

public class TxtFileInput extends Input {

    private BufferedReader bufferedReader;

    public TxtFileInput(String path){
        super(path);
    }

    public TxtFileInput(){
        super();
    }

    @Override
    public boolean openFile(String path) throws IOException {
       
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
    public ArrayList<String> convertFileToStringArray(String path) throws IOException {


        if(!this.openFile(path)){
            throw new IOException("No such file or directory.");
        }

        ArrayList<String> array = new ArrayList<String>();

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                array.add(line);
            }

            return array;
       
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return null;
    }

}
