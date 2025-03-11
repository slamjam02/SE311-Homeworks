package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public class Start extends State {

    public Start(){
        super(null);
        super.currentState = "Start";
        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        
        if(Character.isDigit(input.charAt(0))){
            return new GetFirstOp(new AtomicExpression(Double.parseDouble(input)));
        } else {
            return new Start();
        }

    }
    
}
