package jrw442.Calculator.State;

public class Error extends State{

    private State priorState;

    public Error(State priorState){
        super("ERROR\n[C: RESET]\n[ANY OTHER KEY: DISCARD]");
        super.currentState = "Error";

        this.priorState = priorState;

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    @Override
    public State getNextState(String input) {
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (inputChar == 'C'){
            return new Start("");
        } else {
            return priorState;
        }
    }

}