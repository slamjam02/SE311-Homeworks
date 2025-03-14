import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private Socket client;
    private Logger logger;

    public RequestHandler(Socket client, Logger logger) {
        this.client = client;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            handle();  // your existing code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handle() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        while(client.isConnected()){
            String equation = br.readLine();

            if (equation != null){
                if (!equation.isEmpty()){
                    System.out.println("Successful equation logged: " + equation);
                    logger.write(equation);
                }
            }
        }
        System.out.println("Closing connection to " + client.getInetAddress());
        client.close();

    }

}
