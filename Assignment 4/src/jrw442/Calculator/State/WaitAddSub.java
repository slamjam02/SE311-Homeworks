package jrw442.Calculator.State;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Composite.MulDivExpression;

public class WaitAddSub extends State{

    public WaitAddSub(Expression expression) {
        super(expression);
        super.currentState = "Waiting for add-sub operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            super.currentExpression.enterDigit(Integer.parseInt(input));
            return new GetAddSub(super.currentExpression);
        } else if (inputChar == 'C') {
            return new Start();
        } else {
            return new Error(super.currentExpression);
        }

    }

}
