package jrw442.Calculator.State;

public class Error extends State{


    public Error(StateContext context, State priorState){
        super(context);
        super.currentState = "Error";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: " + super.getCurrentText());

    }

    @Override
    public State getNextState(String input) {
        return null;
    }



}