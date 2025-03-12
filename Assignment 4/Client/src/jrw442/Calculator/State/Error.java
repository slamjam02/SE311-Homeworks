package jrw442.Calculator.State;
import jrw442.Calculator.Observer.StateContext;

public class Error extends State{

    private State priorState;
    private boolean shouldReset;


    public Error(StateContext context, State priorState){
        super(context);
        super.currentState = "Error";

        this.priorState = priorState;

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: " + super.getCurrentText());

    }

    @Override
    public State getNextState(String input) {
        return null;
    }

    public void shouldReset(boolean bool){
        this.shouldReset = bool;
    }

}