package jrw442.Calculator.State;

public class GetAddSub extends State{

    public GetAddSub(String currentText) {
        super(currentText);
        super.currentState = "Calculating";
    }
    
        @Override
    public State getNextState(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextState'");
    }

}