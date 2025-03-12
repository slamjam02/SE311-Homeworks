package jrw442.Calculator.Observer;

import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.State.*;
import jrw442.Calculator.State.Error;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class CalculatorDriver implements ActionListener, StateContext{
    private boolean errorOccurred;
    private String displayText;

    private State currentState;
    private CalculatorView calculatorView;
    private Expression currentExpression;

    public CalculatorDriver(CalculatorView calculatorView){
        this.currentExpression = null;
        this.currentState = new Start(this);
        this.calculatorView = calculatorView;
        this.displayText = "";
        this.errorOccurred = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.currentState = currentState.getNextState(((JButton) e.getSource()).getText());


        if(this.currentState instanceof Start){
            this.displayText = "";
            calculatorView.updateDisplay(this.displayText);
            this.errorOccurred = false;
        } else if (this.currentState instanceof Error){
            calculatorView.updateDisplay("""
                    Error
                    C: Reset
                    Other key: Discard
                    """);
            this.errorOccurred = true;
        } else if (this.currentState instanceof Calculate){
            this.displayText = this.currentState.getResult();
            calculatorView.updateDisplay(this.displayText);
            this.displayText = "";
            this.errorOccurred = false;
        }
        else {
            if (!errorOccurred) {
                displayText += ((JButton) e.getSource()).getText();
            }
            calculatorView.updateDisplay(displayText);
            this.errorOccurred = false;
        }

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


    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            CalculatorView calculatorView = new CalculatorView();
            calculatorView.setVisible(true);
            
            CalculatorDriver calculatorDriver = new CalculatorDriver(calculatorView);
            calculatorView.attach(calculatorDriver);
            
        });
    }

}
