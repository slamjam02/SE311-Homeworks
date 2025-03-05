package jrw442.Calculator.State;

public class Error extends State{

    public Error(String currentText){
        super(currentText);
        super.currentState = "Calculating";
    }

    @Override
    public State getNextState(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextState'");
    }

}