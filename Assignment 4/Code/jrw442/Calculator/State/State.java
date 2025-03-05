package jrw442.Calculator.State;

public abstract class State {
    protected String currentState;
    protected String currentText;

    public State(String currentText) {
        this.currentText = currentText;

    }

    public abstract State getNextState(String input);

    public String getCurrentText(){
        return this.currentText;
    }
}
