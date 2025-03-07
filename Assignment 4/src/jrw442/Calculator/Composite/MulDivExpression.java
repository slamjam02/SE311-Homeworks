package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class MulDivExpression extends Expression{

    private MulDivExpression left;
    private MulDivExpression right;
    private char operator;

    public MulDivExpression() {
        
    }

    public MulDivExpression(Expression left, Expression right, char operator) {
        this.left = (MulDivExpression) left;
        this.right = (MulDivExpression) right;
        this.operator = operator;
    }

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

    public char getOperator(){
        return this.operator;
    }

    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }

}
