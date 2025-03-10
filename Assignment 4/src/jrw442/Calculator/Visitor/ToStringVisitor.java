package jrw442.Calculator.Visitor;

import jrw442.Calculator.Composite.AddSubExpression;
import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.MulDivExpression;

public class ToStringVisitor implements ExpressionVisitor {
    private StringBuilder sb = new StringBuilder();

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void visit(AtomicExpression expr) {
        sb.append(expr.getValue());
    }

    @Override
    public void visit(AddSubExpression expr) {
        //sb.append("(");
        expr.getLeft().acceptVisitor(this);
        sb.append(" " + expr.getOperator() + " ");
        expr.getRight().acceptVisitor(this);
        //sb.append(")");
    }

    @Override
    public void visit(MulDivExpression expr) {
        //sb.append("(");
        expr.getLeft().acceptVisitor(this);
        sb.append(" " + expr.getOperator() + " ");
        expr.getRight().acceptVisitor(this);
        //sb.append(")");
    }

    
}
