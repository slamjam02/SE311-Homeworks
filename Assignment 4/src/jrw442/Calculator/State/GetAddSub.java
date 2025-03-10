package jrw442.Calculator.State;
import jrw442.Calculator.Composite.*;


public class GetAddSub extends State{

    public GetAddSub(Expression expression) {
        super(expression);
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            AddSubExpression expr = (AddSubExpression) currentExpression;
            double newValue = appendDigit(((AtomicExpression) expr.getRight()).getValue(), input);
            return new GetAddSub(new AddSubExpression(expr.getLeft(), new AtomicExpression(newValue), expr.getOperator()));
        } else if (input == '+' || input == '-') {
            return new WaitAddSub(currentExpression, input);
        } else if (input == '*' || input == '/') {
            return new WaitMulDiv(currentExpression, input);
        } else if (input == '=') {
            return new Calculate(currentExpression);
        }
        return new Error(this);
    }

    private double appendDigit(double current, char newDigit) {
        return current * 10 + Character.getNumericValue(newDigit);
    }
}