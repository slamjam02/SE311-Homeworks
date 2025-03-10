package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;

public class WaitMulDiv extends State{
    private char operator;

    public WaitMulDiv(Expression left, char operator) {
        super(left);
        this.operator = operator;
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            ExpressionBuilderVisitor visitor = new ExpressionBuilderVisitor(currentExpression, input, true);
            currentExpression.acceptVisitor(visitor);
            return new GetMulDiv(visitor.getModifiedExpression());
        }
        return new Error(this);
    }

    @Override
    public String getExpressionString() {
        return getExpressionStringWithOperator(operator);
    }
}
