package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Observer.CalculatorDriver;
import jrw442.Calculator.Observer.StateContext;

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
