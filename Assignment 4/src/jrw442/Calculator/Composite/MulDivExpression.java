package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class MulDivExpression extends Expression {
    private Expression left;
    private Expression right;
    private char operator;

    public MulDivExpression(Expression left, Expression right, char operator) {
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
            // Lower precedence: promote current node as a left child of the new addition/subtraction
            AddSubExpression newRoot = new AddSubExpression(this, null, newOperator);
            right = newRoot;
        } else {
            // Higher precedence: continue nesting multiplication/division
            right = new MulDivExpression((MulDivExpression) right, null, newOperator);
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

    @Override
    public char getOperator(){
        return this.operator;
    }

    @Override
    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }

    @Override
    public void setRight(Expression expression){
        this.right = (MulDivExpression) expression;
    }

    @Override
    public void setLeft(Expression expression){
        this.left = (MulDivExpression) expression;
    }

    @Override
    public String toString() {
        String leftStr = (left == null) ? "?" : left.toString();
        String rightStr = (right == null) ? "?" : right.toString();
        
        return "(" + leftStr + " " + operator + " " + rightStr + ")";
    }
}