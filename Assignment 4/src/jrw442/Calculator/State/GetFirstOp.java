package jrw442.Calculator.State;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.Expression;

public class GetFirstOp extends State{

    public GetFirstOp(Expression expression) {
        super(expression);
        super.currentState = "Getting first operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());
    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            super.currentExpression.enterDigit(Integer.parseInt(input));
            return new GetFirstOp(super.currentExpression);
        } else if (inputChar == '+' || inputChar == '-'){
            super.currentExpression = new AddSubExpression(currentExpression, null, inputChar);
            return new WaitAddSub(super.currentExpression);
        } else if (inputChar == '*' || inputChar == '/'){
            super.currentExpression.enterOperator(input.charAt(0));
            return new WaitMulDiv(super.currentExpression);
        } else if (inputChar == '='){
            return new Calculate(super.currentExpression);
        } else {
            return new Start();
        }
    }

}
