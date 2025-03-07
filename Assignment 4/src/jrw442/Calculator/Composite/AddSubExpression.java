package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class AddSubExpression extends Expression{

    private Expression left;
    private Expression right;

    @Override
    public void addLeft(Expression e) {
        this.left = e;
    }

    @Override
    public void addRight(Expression e) {
        this.right = e;
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
