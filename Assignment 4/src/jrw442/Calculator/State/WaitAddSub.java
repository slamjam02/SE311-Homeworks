package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;

public class WaitAddSub extends State{

    public WaitAddSub(Expression currentExpression) {
        super(currentExpression);
        super.currentState = "Waiting for add-sub operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            return new GetAddSub(currentText + input);
        } else if (inputChar == 'C') {
            return new Start("");
        } else {
            return new Error(this);
        }
    }

}
