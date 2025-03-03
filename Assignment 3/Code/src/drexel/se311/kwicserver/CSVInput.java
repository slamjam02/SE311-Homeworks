package drexel.se311.kwicserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVInput extends Input{

    private BufferedReader bufferedReader;


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
            String fileLine;
            String fileText = "";

            while ((fileLine = bufferedReader.readLine()) != null) {
                fileText = fileText + fileLine;
            }

            String[] lines = fileText.split("\\.");

            for (String line : lines) {
                array.add(line);
            }
            return array;
       
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return null;
    }
    
}
