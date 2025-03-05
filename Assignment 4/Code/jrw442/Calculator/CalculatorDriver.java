package jrw442.Calculator;

import jrw442.Calculator.State.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class CalculatorDriver implements ActionListener{
    private State currentState;
    private CalculatorView calculatorView;

    public CalculatorDriver(CalculatorView calculatorView){
        this.currentState = new Start("");
        this.calculatorView = calculatorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.currentState = currentState.getNextState(((JButton) e.getSource()).getText());
        calculatorView.updateDisplay(this.currentState.getCurrentText());
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            CalculatorView calculatorView = new CalculatorView();
            CalculatorDriver calculatorDriver = new CalculatorDriver(calculatorView);
            calculatorView.attach(calculatorDriver);
            calculatorView.setVisible(true);
        });
    }
}
