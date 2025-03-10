package jrw442.Calculator.State;
import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Visitor.ExpressionBuilderVisitor;


public class GetFirstOp extends State{

    public GetFirstOp(Expression expression) {
        super(expression);
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            ExpressionBuilderVisitor visitor = new ExpressionBuilderVisitor(currentExpression, input, false);
            currentExpression.acceptVisitor(visitor);
            return new GetFirstOp(visitor.getModifiedExpression());
        } else if (input == '+' || input == '-') {
            return new WaitAddSub(currentExpression, input);
        } else if (input == '*' || input == '/') {
            return new WaitMulDiv(currentExpression, input);
        }
        return new Error(this);
    }
}
