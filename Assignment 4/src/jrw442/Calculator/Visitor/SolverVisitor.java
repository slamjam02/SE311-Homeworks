package jrw442.Calculator.Visitor;

import jrw442.Calculator.Composite.*;

public class SolverVisitor implements ExpressionVisitor {
    private double result;

    public SolverVisitor(){
        
    }

    public double getResult() {
        return result;
    }

    public String getResultString() {
        if (result % 1 == 0) {
            return String.format("%.0f", result); // No decimals if it's a whole number
        } else {
            return String.valueOf(result); // Default string conversion
        }
    }

    @Override
    public void visit(AtomicExpression expr) {
        result = expr.getValue();
    }

    @Override
    public void visit(AddSubExpression expr) {
        expr.getLeft().acceptVisitor(this);
        double leftResult = result;

        expr.getRight().acceptVisitor(this);
        double rightResult = result;

        result = (expr.getOperator() == '+') ? leftResult + rightResult : leftResult - rightResult;
    }

    @Override
    public void visit(MulDivExpression expr) {
        expr.getLeft().acceptVisitor(this);
        double leftResult = result;

        expr.getRight().acceptVisitor(this);
        double rightResult = result;

        result = (expr.getOperator() == '*') ? leftResult * rightResult : leftResult / rightResult;
    }
}