package jrw442.Calculator.State;

import jrw442.Calculator.Visitor.ToStringVisitor;

public class Error extends State{


    private final State previousState;

    public Error(State previousState) {
        super(previousState != null ? previousState.getCurrentExpression() : null);
        this.previousState = previousState;
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (input == 'C') {
            return new Start();
        }
        return previousState != null ? previousState : new Start();
    }
   
    public String getExpressionString() {
        return """
                ERROR
                [C: RESET]
                [ANY OTHER KEY: DISCARD]
                """;
    }

 
}