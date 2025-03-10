package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Visitor.ToStringVisitor;

public abstract class State {
    protected Expression currentExpression;

    public State(Expression currentExpression) {
        this.currentExpression = currentExpression;
    }

    public abstract State getNextState(char input);

    public Expression getCurrentExpression() {
        return currentExpression;
    }

    protected String getExpressionStringWithOperator(char operator) {
        if (currentExpression == null) return "" + operator;
        ToStringVisitor visitor = new ToStringVisitor();
        currentExpression.acceptVisitor(visitor);
        return visitor.getResult() + " " + operator;
    }

    public String getExpressionString() {
        if (currentExpression == null) return "";
        ToStringVisitor toStringVisitor = new ToStringVisitor();
        currentExpression.acceptVisitor(toStringVisitor);
        return toStringVisitor.getResult();
    }

    protected void logState(char input) {
        System.out.println("Input: '" + input + "' | State: " + this.getClass().getSimpleName() + 
                           " | Expression: " + getExpressionString());
    }
}
