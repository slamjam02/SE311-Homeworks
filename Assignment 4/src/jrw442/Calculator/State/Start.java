package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Visitor.ToStringVisitor;

public class Start extends State {

    public Start() {
        super(null);
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            return new GetFirstOp(new AtomicExpression(Character.getNumericValue(input)));
        } else if (input == 'C'){
            return new Start();
        }
        return new Error(this);
    }

    @Override
    public String getExpressionString() {
        return "";
    }
    
}
