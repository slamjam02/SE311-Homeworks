import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame {
    private JTextArea outputArea;

    public CalculatorUI() {
        setTitle("Calculator UI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setSize(320, 400);
        setLocationRelativeTo(null);

        // Create the output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        outputArea = new JTextArea(3, 20);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 24));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "0", "=", "C", "/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        // Add panels to the frame
        setLayout(new BorderLayout(20, 10));
        add(outputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            outputArea.append(source.getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calculator = new CalculatorUI();
            calculator.setVisible(true);
        });
    }
}
