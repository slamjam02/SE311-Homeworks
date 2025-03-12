package jrw442.Calculator.Visitor;


import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.MulDivExpression;

public class SolveVisitor implements ExpressionVisitor {
    private double result;

    public double getResult() {
        return this.result;
    }

    public String getResultString() {
        if (result % 1 == 0) {
            return String.format("%.0f", result); // No decimals if it's a whole number
        } else {
            return String.valueOf(result); // Default string conversion
        }
    }

    @Override
    public void visit(AtomicExpression exp) {
        result = exp.getValue();
    }

    @Override
    public void visit(AddSubExpression exp) {
        SolveVisitor leftVisitor = new SolveVisitor();
        SolveVisitor rightVisitor = new SolveVisitor();

        exp.getLeft().acceptVisitor(leftVisitor);
        exp.getRight().acceptVisitor(rightVisitor);

        if (exp.getOperator() == '+') {
            result = leftVisitor.getResult() + rightVisitor.getResult();
        } else {
            result = leftVisitor.getResult() - rightVisitor.getResult();
        }
    }

    @Override
    public void visit(MulDivExpression exp) {
        SolveVisitor leftVisitor = new SolveVisitor();
        SolveVisitor rightVisitor = new SolveVisitor();

        exp.getLeft().acceptVisitor(leftVisitor);
        exp.getRight().acceptVisitor(rightVisitor);

        if (exp.getOperator() == '*') {
            result = leftVisitor.getResult() * rightVisitor.getResult();
        } else {
            result = leftVisitor.getResult() / rightVisitor.getResult();
        }
    }

}