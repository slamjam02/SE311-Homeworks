package jrw442.Calculator.Visitor;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.MulDivExpression;

public class SolveVisitor implements ExpressionVisitor {
    private double result;

    public double getResult() {
        return result;
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