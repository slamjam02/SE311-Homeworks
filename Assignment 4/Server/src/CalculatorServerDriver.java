import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServerDriver {

    private static int port = 5555;
    private static String logFile = "log.txt";

    public static void main(String[] args){
        // If the user passed a port on the command line, parse it
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number. Using default: " + port);
            }
        }

        // Create our server socket (only once)
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("\nListening for connections on port " + port + "...\n");
            System.out.println("You may enter \"print\" to view current log or \"quit\" to exit the server.");
            Logger logger = new Logger(logFile);

            // Start a separate thread to handle console input
            Thread consoleThread = new Thread(() -> {
                try (BufferedReader consoleReader = 
                        new BufferedReader(new InputStreamReader(System.in))) {

                    String line;
                    while ((line = consoleReader.readLine()) != null) {
                        if (line.equalsIgnoreCase("print")) {
                            // The user typed "print" in the console => read and display the logs
                            printLogContents(logFile);
                        } else if (line.equalsIgnoreCase("quit")) {
                            // Let the user shut down the server from console
                            System.out.println("Shutting down server...");
                            System.exit(0); 
                        } else {
                            System.out.println("Unknown command. Try 'print' or 'quit'.");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            consoleThread.start();

            // The main thread handles incoming client connections in a loop
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Connected to new client: " + client.getInetAddress());
                
                // Launch a separate thread (RequestHandler) for each client
                RequestHandler requestHandler = new RequestHandler(client, logger);
                requestHandler.start();
            }

        } catch (IOException e) {
            System.err.println("Socket establishment failed: \n" + e.getMessage());
        }
    }

    private static void printLogContents(String fileName) {
        System.out.println("=== Contents of " + fileName + " ===");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String logLine;
            while ((logLine = br.readLine()) != null) {
                System.out.println(logLine);
            }
        } catch (IOException e) {
            System.err.println("Error reading " + fileName + ": " + e.getMessage());
        }
        System.out.println("====================================\n");
    }
}