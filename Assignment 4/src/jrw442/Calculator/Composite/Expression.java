package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public abstract class Expression {
    public Expression(){

    }

    public abstract void addLeft(Expression e);
    public abstract void addRight(Expression e);
    public abstract Expression getLeft();
    public abstract Expression getRight();
    public void acceptVisitor(ExpressionVisitor v){
        v.visit(this);
    }

}
