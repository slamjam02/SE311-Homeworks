package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public abstract class State {
    protected String currentState;
    protected Expression currentExpression;

    public State(Expression expression) {
        this.currentExpression = expression;
    }

    public abstract State getNextState(String input);

    public String getCurrentText(){
        return this.currentExpression.toString();
    }
}
