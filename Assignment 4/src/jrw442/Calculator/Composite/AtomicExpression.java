package jrw442.Calculator.Composite;

import jrw442.Calculator.Visitor.ExpressionVisitor;

public class AtomicExpression extends Expression {
    private double value;

    public AtomicExpression(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void enterDigit(int digit) {
        // Append digit to existing value (e.g., 2 -> 22 when '2' is pressed again)
        this.value = this.value * 10 + digit;
    }

    @Override
    public void enterOperator(char operator) {
        throw new IllegalStateException("Cannot enter operator immediately after a number without a parent expression.");
    }

    @Override
    public Expression getLeft() {
        return null; // Leaf node
    }

    @Override
    public Expression getRight() {
        return null; // Leaf node
    }

    @Override
    public void acceptVisitor(ExpressionVisitor v) {
        v.visit(this);
    }
}