
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

    String filename;

    public Logger(String filename){
        this.filename = filename;
    }

    public void write(String text) throws IOException {
        File file = new File(filename);
        System.out.println("File exists: " + file.exists());
        int successes = 0;
        StringBuilder logEntries = new StringBuilder();

        // Step 1: Read existing log file
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                if ((line = br.readLine()) != null && line.startsWith("Successes:")) {
                    successes = Integer.parseInt(line.split(": ")[1]);
                }
                while ((line = br.readLine()) != null) {
                    logEntries.append(line).append("\n");
                }
            } catch (Exception e) {
                System.err.println("Log file is not formatted properly. Please delete or specify a different filename.");
                return;
            }
        }

        // Step 2: Update success

        successes++;
        logEntries.append(text).append("\n");

        // Step 3: Rewrite the log file with updated counts and new entries
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("Successes: " + successes);
            bw.newLine();
            bw.write(logEntries.toString()); // Append previous log entries
        }
    }
    
}
