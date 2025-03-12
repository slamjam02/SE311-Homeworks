package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;

public class Error extends State{

    private State priorState;

    public Error(StateContext context){
        super(context);
        super.currentState = "Error";

        this.priorState = priorState;

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (inputChar == 'C'){
            return new Start();
        } else {
            return priorState;
        }
    }

}