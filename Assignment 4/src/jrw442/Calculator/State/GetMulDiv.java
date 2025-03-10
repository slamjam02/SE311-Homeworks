package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;


public class GetMulDiv extends State{

    public GetMulDiv(Expression expression) {
        super(expression);
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            MulDivExpression expr = (MulDivExpression) currentExpression;
            double newValue = appendDigit(((AtomicExpression) expr.getRight()).getValue(), input);
            return new GetMulDiv(new MulDivExpression(expr.getLeft(), new AtomicExpression(newValue), expr.getOperator()));
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

