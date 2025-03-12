package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public class Start extends State {

    public Start(StateContext context){
        super(context);
        super.currentState = "Start";
        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent tree: ");
    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);


        if(Character.isDigit(input.charAt(0))){
            return new GetFirstOp(new AtomicExpression(Double.parseDouble(input)));
        } else {
            return new Start(context);
        }

    }
    
}
