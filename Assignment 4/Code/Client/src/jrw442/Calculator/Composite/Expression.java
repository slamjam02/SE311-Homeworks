package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public abstract class Expression {
    public abstract void enterDigit(int digit);
    public abstract void enterOperator(char operator);
    public abstract char getOperator();
    public abstract Expression getLeft();
    public abstract Expression getRight();
    public abstract void acceptVisitor(ExpressionVisitor v);
    public abstract void setRight(Expression expression);
    public abstract void setLeft(Expression expression);
}