package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Observer.StateContext;

public class GetFirstOp extends State{

    public GetFirstOp(StateContext context) {
        super(context);
        super.currentState = "Getting first operand";
        System.out.println("\nCurrent state: " + super.currentState + "\nCurrent expression: " + super.getCurrentText());
    }

    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            context.getCurrentExpression().enterDigit(Character.getNumericValue(inputChar));
            return new GetFirstOp(context);
        } else if (inputChar == '*' || inputChar == '/'){
            context.setCurrentExpression(new MulDivExpression(context.getCurrentExpression(), null, inputChar));
            return new WaitMulDiv(context);
        } else if (inputChar == '+' || inputChar == '-'){
            context.setCurrentExpression(new AddSubExpression(context.getCurrentExpression(), null, inputChar));
            return new WaitAddSub(context);
        } else if (inputChar == '='){
            return new Calculate(context);
        } else {
            return new Start(context);
        }
    }
}