package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;

public class WaitMulDiv extends State{

    public WaitMulDiv(Expression expression) {
        super(expression);
        super.currentState = "Waiting for mul-div operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            super.currentExpression.enterDigit(Integer.parseInt(input));
            return new GetMulDiv(super.currentExpression);
        } else if (inputChar == '+' || inputChar == '-'){
            return new Error(super.currentExpression);
        } else if (inputChar == '*' || inputChar == '/'){
            return new Error(super.currentExpression);
        } else if (inputChar == '='){
            return new Error(super.currentExpression);
        } else {
            return new Start();
        }
    }
 

}