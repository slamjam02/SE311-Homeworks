package jrw442.Calculator.Visitor;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.MulDivExpression;

public interface ExpressionVisitor {

    public abstract void visit(AtomicExpression exp);

    public abstract void visit(AddSubExpression exp);

    public abstract void visit(MulDivExpression exp);

}