package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Visitor.ToStringVisitor;

public class WaitAddSub extends State{

    private char operator;

    public WaitAddSub(Expression left, char operator) {
        super(left);
        this.operator = operator;
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            return new GetAddSub(new AddSubExpression(currentExpression, new AtomicExpression(Character.getNumericValue(input)), operator));
        }
        return new Error(this);
    }

    @Override
    public String getExpressionString() {
        return getExpressionStringWithOperator(operator);
    }
}
