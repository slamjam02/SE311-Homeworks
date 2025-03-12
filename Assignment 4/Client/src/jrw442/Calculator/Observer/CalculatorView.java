package jrw442.Calculator.Observer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorView extends JFrame {
    private JTextArea outputArea;
    private ArrayList<ActionListener> listeners;
    private JPanel buttonPanel;

    public CalculatorView() {
        listeners = new ArrayList<ActionListener>();
        
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Create the output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputArea = new JTextArea(3, 20);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 28));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Create the button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "0", "=", "C", "/"
        };

        // Make buttons
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            buttonPanel.add(button);
        }

        // Add panels to the frame
        setLayout(new BorderLayout(20, 10));
        add(outputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    

    public void attach(ActionListener listener){
        this.listeners.add(listener);
        for(Component component : buttonPanel.getComponents()){
            JButton button = (JButton) component;
            button.addActionListener(listener);
        }
    }


    public void updateDisplay(String displayText) {
        this.outputArea.setText(displayText);
    }

    public boolean popUp(String body, String button1, String button2) {
        Object[] options = {button1, button2}; // Custom button labels
    
        int choice = JOptionPane.showOptionDialog(
            null,                    // Parent component (null for center of screen)
            body,                    // Message body
            "Confirmation",          // Title of the dialog
            JOptionPane.YES_NO_OPTION, // Dialog type
            JOptionPane.QUESTION_MESSAGE, // Icon type
            null,                    // Custom icon (null for default)
            options,                 // Custom button text
            options[0]               // Default selected option
        );
    
        return choice == 0; // Returns true if button1 is clicked, false if button2 is clicked
    }

    

}
