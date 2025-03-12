package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public class GetMulDiv extends State{

    public GetMulDiv(Expression expression) {
        super(expression);
        super.currentState = "Getting mul-div operand";
        System.out.println("\nCurrent state: " + super.currentState + "\nCurrent string: " + super.getCurrentText());
    }
    
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
        Character inputChar = input.charAt(0);

        if (Character.isDigit(inputChar)) {
            super.currentExpression.enterDigit(Character.getNumericValue(inputChar));
            return new GetMulDiv(super.currentExpression);
        } 
        else if (inputChar == '*' || inputChar == '/') {
            return new WaitMulDiv(new MulDivExpression(this.currentExpression, null, inputChar));
        } 
        else if (inputChar == '+' || inputChar == '-') {
            // Instead of replacing the tree, wrap `MulDivExpression` inside a new `AddSubExpression`
            return new WaitAddSub(new AddSubExpression(this.currentExpression, null, inputChar));
        } 
        else if (inputChar == '=') {
            return new Calculate(super.currentExpression);
        } 
        else {
            return new Start(); // Handle errors or invalid characters
        }
    }
}