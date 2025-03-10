package jrw442.Calculator.State;

import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Visitor.*;

public class Calculate extends State{

    public Calculate(Expression expression) {
        super(expression);
    }

    @Override
    public State getNextState(char input) {
        logState(input);
        if (Character.isDigit(input)) {
            return new GetFirstOp(new AtomicExpression(Character.getNumericValue(input)));
        }
        return new Start();
    }

    @Override
    public String getExpressionString() {
        SolverVisitor solver = new SolverVisitor();
        currentExpression.acceptVisitor(solver);
        return String.valueOf(solver.getResult());
    }
}