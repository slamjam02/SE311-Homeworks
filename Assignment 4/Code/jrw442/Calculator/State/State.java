package jrw442.Calculator.State;

public abstract class State {
    public State() {

    }

    public abstract State getNextState(String input);

    public abstract String getDisplayText();
}
