package jrw442.Calculator.Visitor;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Composite.MulDivExpression;

public class ExpressionBuilderVisitor implements ExpressionVisitor {
    private Expression modifiedExpression;
    private char operator;
    private boolean isNewNumber;

    public ExpressionBuilderVisitor(Expression currentExpression, char operator, boolean isNewNumber) {
        this.modifiedExpression = currentExpression;
        this.operator = operator;
        this.isNewNumber = isNewNumber;
    }

    public Expression getModifiedExpression() {
        return modifiedExpression;
    }

    @Override
    public void visit(AtomicExpression expr) {
        if (Character.isDigit(operator)) {
            double newValue = expr.getValue() * 10 + Character.getNumericValue(operator);
            modifiedExpression = new AtomicExpression(newValue);
        }
    }

    @Override
    public void visit(AddSubExpression expr) {
        if (Character.isDigit(operator)) {
            Expression right = expr.getRight();
            if (right instanceof AtomicExpression && !isNewNumber) {
                right.acceptVisitor(this);
                modifiedExpression = new AddSubExpression(expr.getLeft(), modifiedExpression, expr.getOperator());
            } else {
                modifiedExpression = new AddSubExpression(expr, new AtomicExpression(Character.getNumericValue(operator)), this.operator);
            }
        } else if (operator == '*' || operator == '/') {
            Expression right = expr.getRight();
            modifiedExpression = new AddSubExpression(expr.getLeft(), new MulDivExpression(right, new AtomicExpression(0), operator), expr.getOperator());
        }
    }

    @Override
    public void visit(MulDivExpression expr) {
        if (Character.isDigit(operator)) {
            Expression right = expr.getRight();
            if (right instanceof AtomicExpression && !isNewNumber) {
                right.acceptVisitor(this);
                modifiedExpression = new MulDivExpression(expr.getLeft(), modifiedExpression, expr.getOperator());
            } else {
                modifiedExpression = new MulDivExpression(expr, new AtomicExpression(Character.getNumericValue(operator)), this.operator);
            }
        }
    }
}