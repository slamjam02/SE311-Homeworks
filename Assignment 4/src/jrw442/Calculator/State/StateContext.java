package jrw442.Calculator.State;

import java.beans.Expression;

public interface StateContext {
    public abstract void resetCurrentExpression();

    public abstract Expression getCurrentExpression();

    public abstract void setCurrentExpression(Expression e); 
    
} 
