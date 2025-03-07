package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class MulDivExpression extends Expression{

    private MulDivExpression left;
    private MulDivExpression right;

    @Override
    public void addLeft(Expression e) {
        this.left = (MulDivExpression) e;
    }

    @Override
    public void addRight(Expression e) {
        this.right = (MulDivExpression) e;
    }

    @Override
    public Expression getLeft() {
        return this.left;
    }

    @Override
    public Expression getRight() {
        return this.right;
    }

    @Override
    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }

}
