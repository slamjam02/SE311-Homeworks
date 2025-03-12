package jrw442.Calculator.State;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Composite.MulDivExpression;
import jrw442.Calculator.Observer.StateContext;

public class WaitAddSub extends State{

    public WaitAddSub(StateContext context) {
        super(context);
        super.currentState = "Waiting for add-sub operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            context.getCurrentExpression().enterDigit(Integer.parseInt(input));
            return new GetAddSub(context);
        } else if (inputChar == 'C') {
            return new Start(context);
        } else {
            return new Error(context, this);
        }

    }

}
