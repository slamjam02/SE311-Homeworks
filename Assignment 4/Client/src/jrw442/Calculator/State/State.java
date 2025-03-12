package jrw442.Calculator.State;

public abstract class State {
    protected String currentState;
    protected StateContext context;

    public State(StateContext context) {
        this.context = context;
    }

    public abstract State getNextState(String input);

    public String getCurrentText(){
        return this.context.getCurrentExpression().toString();
    }

    public String getResult(){
        return null;
    }
}
