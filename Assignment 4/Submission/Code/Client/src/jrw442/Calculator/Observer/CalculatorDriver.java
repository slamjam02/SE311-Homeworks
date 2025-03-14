package jrw442.Calculator.Observer;

import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.State.*;
import jrw442.Calculator.State.Error;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class CalculatorDriver implements ActionListener, StateContext{
    private boolean calculationOccurred;
    private String displayText;
    private State currentState;
    private CalculatorView calculatorView;
    private Expression currentExpression;

    private Socket finalSocket;
    private static String ipAddress = "localhost";
    private static int port = 5555;

    public CalculatorDriver(CalculatorView calculatorView, Socket finalSocket){
        this.currentExpression = null;
        this.currentState = new Start(this);
        this.calculatorView = calculatorView;
        this.displayText = "";
        this.calculationOccurred = false;
        this.finalSocket = finalSocket;

    }

    public boolean getErrorPopupInput(){
        return this.calculatorView.popUp("ERROR\nAn invalid input was pressed.", "Reset", "Discard");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        State priorState = this.currentState;
        this.currentState = currentState.getNextState(((JButton) e.getSource()).getText());
    
        if (this.currentState instanceof Start) {
            this.displayText = "";
            this.calculationOccurred = false;
        } 
        else if (this.currentState instanceof Error) {
            boolean shouldReset = getErrorPopupInput(); // Ask user whether to reset
            if (shouldReset) {
                this.displayText = "";  // Clear display if user chooses to reset
                this.currentExpression = null;
                this.currentState = new Start(this); // Reset state to start
            } else {
                this.currentState = priorState;
            }
            this.calculationOccurred = false; // Clear error flag after handling
        } 
        else if (this.currentState instanceof Calculate) {
            if(finalSocket != null && finalSocket.isConnected()){
                try {

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.finalSocket.getOutputStream()));
                    bw.write(this.displayText+"="+this.currentState.getResult() + "\n");
                    bw.flush();
                    System.out.println("Sending successful equation to server: " + this.displayText + "=" + this.currentState.getResult());

                } catch (IOException e1) {
                    System.err.println("Buffered reader failed to send successful equation to server.");
                }
            
            }
            this.displayText = this.currentState.getResult();
            this.calculationOccurred = true;
        } 
        else {
            
            if(calculationOccurred){
                displayText = ((JButton) e.getSource()).getText();
            } else {
                displayText += ((JButton) e.getSource()).getText();
            }
            this.calculationOccurred = false;
        }
    
        calculatorView.updateDisplay(this.displayText);
    }

    public void resetCurrentExpression() {
        this.currentExpression = null;
    }

    public Expression getCurrentExpression(){
        return this.currentExpression;
    }

    public void setCurrentExpression(Expression e){
        this.currentExpression = e;
    }


    public static void main(String[] args) {
        Socket socket = null;
    
        // Parse command-line arguments if provided
        if (args.length > 0) {
            ipAddress = args[0];
            if (args.length > 1) {
                try {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid port number. Using default: " + port);
                }
            }
        }
    
        // Attempt to connect to the socket server
        try {
            socket = new Socket(ipAddress, port);
            System.out.println("Connected to server at " + ipAddress + ":" + port);
        } catch (IOException e) {
            System.err.println("Failed to connect to " + ipAddress + ":" + port + ". Server logging disabled.");
            System.err.println("You may still use the application or restart to try again.");
        }
    
        // Final reference to socket for shutdown hook
        final Socket finalSocket = socket;
    
        // Register a shutdown hook to close the socket when the application quits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (finalSocket != null && !finalSocket.isClosed()) {
                try {
                    finalSocket.close();
                    System.out.println("Socket closed on application exit.");
                } catch (IOException ex) {
                    System.err.println("Error closing socket on exit: " + ex.getMessage());
                }
            }
        }));
    
        // Launch Swing UI
        SwingUtilities.invokeLater(() -> {
            CalculatorView calculatorView = new CalculatorView();
            calculatorView.setVisible(true);
    
            CalculatorDriver calculatorDriver = new CalculatorDriver(calculatorView, finalSocket);
            calculatorView.attach(calculatorDriver);
        });
    }

}
