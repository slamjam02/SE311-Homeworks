package jrw442.Calculator.State;

public class WaitMulDiv extends State{

    public WaitMulDiv(String currentText) {
        super(currentText);
        super.currentState = "Waiting for mul-div operand";
    }

    @Override
    public State getNextState(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNextState'");
    }

}