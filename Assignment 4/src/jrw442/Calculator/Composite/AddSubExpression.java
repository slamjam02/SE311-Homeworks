package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class AddSubExpression extends Expression {
    private Expression left;
    private Expression right;
    private char operator;

    public AddSubExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public void enterDigit(int digit) {
        if (right == null) {
            right = new AtomicExpression(digit);
        } else {
            right.enterDigit(digit);
        }
    }

    @Override
    public void enterOperator(char newOperator) {
        if (right == null) {
            throw new IllegalStateException("Cannot enter an operator without a right operand.");
        }

        if (newOperator == '+' || newOperator == '-') {
            // Addition and subtraction are lower precedence, so create a new root
            AddSubExpression newRoot = new AddSubExpression(this, null, newOperator);
            right = newRoot;
        } else {
            // Higher precedence (mul/div), attach it to the right
            right = new MulDivExpression(right, null, newOperator);
        }
    }

    @Override
    public Expression getLeft() {
        return left;
    }

    @Override
    public Expression getRight() {
        return right;
    }

    public char getOperator(){
        return this.operator;
    }

    @Override
    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }
}