package jrw442.Calculator.State;
import jrw442.Calculator.Observer.StateContext;

public class Error extends State{

    private State priorState;


    public Error(StateContext context, State priorState){
        super(context);
        super.currentState = "Error";

        this.priorState = priorState;

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: " + super.getCurrentText());

    }

    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (inputChar == 'C'){
            return new Start(context);
        } else {
            return priorState;
        }
    }

}