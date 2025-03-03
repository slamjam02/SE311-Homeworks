package drexel.se311.kwicserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RequestHandler extends Thread{
    private Socket clientSocket;

    public RequestHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public String runKeywordSearch(Searcher searcher, LineStorage lineStorage){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            bufferedWriter.write("Enter a keyword to search: ");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String wordToFind = bufferedReader.readLine();

            System.out.println("Searching for keyword \"" + wordToFind + "\" for client " + clientSocket.getInetAddress());

            ArrayList<String> foundLines = searcher.search(lineStorage, wordToFind);
            String output = "";

            bufferedWriter.newLine();

            if (foundLines.isEmpty()) {
                bufferedWriter.write(wordToFind + " not found.");
                bufferedWriter.newLine();
            } else {
                bufferedWriter.write(foundLines.size() + " sentence(s) found.");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                for (String foundLine : foundLines) {
                    bufferedWriter.write(foundLine);
                    bufferedWriter.newLine();
                }

                String timeStamp = new SimpleDateFormat("HH:mm:ss MM/dd/YYYY").format(Calendar.getInstance().getTime());
                output = ("Successful keyword search query \"" + wordToFind + "\" for client " + clientSocket.getInetAddress() + " at " + timeStamp);

            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            bufferedReader.close();
            bufferedWriter.close();
            clientSocket.close();

            return output;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}
