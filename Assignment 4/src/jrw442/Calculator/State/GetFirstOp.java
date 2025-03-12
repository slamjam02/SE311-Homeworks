package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public class GetFirstOp extends State{

    public GetFirstOp(StateContext context) {
        super(context);
        super.currentState = "Getting first operand";
        System.out.println("\nCurrent state: " + super.currentState + "\nCurrent string: " + super.getCurrentText());
    }

    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            super.currentExpression.enterDigit(Character.getNumericValue(inputChar));
            return new GetFirstOp(super.currentExpression);
        } else if (inputChar == '*' || inputChar == '/'){
            return new WaitMulDiv(new MulDivExpression(super.currentExpression, null, inputChar));
        } else if (inputChar == '+' || inputChar == '-'){
            return new WaitAddSub(new AddSubExpression(super.currentExpression, null, inputChar));
        } else if (inputChar == '='){
            return new Calculate(super.currentExpression);
        } else {
            return new Start();
        }
    }
}