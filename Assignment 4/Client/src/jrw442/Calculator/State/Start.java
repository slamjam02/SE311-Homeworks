package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Observer.StateContext;

public class Start extends State {

    public Start(StateContext context){
        super(context);
        super.currentState = "Start";
        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: ");
    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);


        if(Character.isDigit(input.charAt(0))){
            context.setCurrentExpression(new AtomicExpression(Double.parseDouble(input)));
            return new GetFirstOp(context);
        } else {
            return new Start(context);
        }

    }
    
}
