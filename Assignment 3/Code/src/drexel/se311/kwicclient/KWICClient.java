package drexel.se311.kwicclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class KWICClient {

    private static String host = "localhost";
    private static int port = 4000;
    private static Scanner scanner;

    public static void setup(){
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception{
        setup();
        System.out.println("Running KWICClient...\n");

        if (args.length == 1){
            host = args[0];
        } else if (args.length > 1){
            host = args[0];
            try{
                port = Integer.parseInt(args[1]);
            } catch (Exception e){
                System.err.println("Port number must be an integer.");
            }
        }


        System.out.println("Server IP: " + host + ":" + port);
        System.out.println("You may use first command line argument to specify an IP address, and the second to specify a custom port.\n");

        

        try{

            System.out.println("Attempting to connect...");
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 30000);
            socket.setSoTimeout(30000);
            if(socket.isConnected()){ System.out.println("Connection successful!"); }


            System.out.println();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String outputString;

            System.out.println(bufferedReader.readLine());

            String inputString = scanner.nextLine();

            bufferedWriter.write(inputString);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while((outputString = bufferedReader.readLine()) != null){
                System.out.println(outputString);
            }

            bufferedReader.close();
            bufferedWriter.close();
            socket.close();

            

        }
        catch(java.net.SocketTimeoutException e){
            System.err.println("The KWIC server is not responding.");
        }
        catch (IOException e){
            System.err.println(e.getMessage() + " on address \"" + host + "\" and port " + port);
        }
        


    }

}
