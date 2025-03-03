import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TxtFileOutput extends Output {

    private String path;
    private PrintWriter writer;

    public TxtFileOutput(){
        super();
        
    }

    public void createFile(String path) throws FileNotFoundException, UnsupportedEncodingException{
        this.path = path;
        writer = new PrintWriter(path, "UTF-8");
    }

    @Override
    public void addLineToOutput(String string) {
        super.outputLines.add(string);
    }

    @Override
    public void complete() {
        for (String string : super.outputLines) {
            this.writer.println(string);
        }
        writer.close();
        System.out.println("Output generated at: " + path);
    }
    
}
