package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class AtomicExpression extends MulDivExpression{

    private double value;

    public AtomicExpression(double value){
        this.value = value;
    }

    public void setValue(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }

    @Override
    public void addLeft(Expression e) {
        throw new UnsupportedOperationException("Cannot add child to leaf node AtomicExpression");
    }

    @Override
    public void addRight(Expression e) {
        throw new UnsupportedOperationException("Cannot add child to leaf node AtomicExpression");
    }

    @Override
    public Expression getLeft() {
        throw new UnsupportedOperationException("Cannot get child from leaf node AtomicExpression");
    }

    @Override
    public Expression getRight() {
        throw new UnsupportedOperationException("Cannot get child from leaf node AtomicExpression");
    }

    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }

}
