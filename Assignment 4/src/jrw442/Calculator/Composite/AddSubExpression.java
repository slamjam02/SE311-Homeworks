package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class AddSubExpression extends Expression{

    private Expression left;
    private Expression right;
    private char operator; 

    public AddSubExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

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

    public char getOperator(){
        return this.operator;
    }

    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }

}
