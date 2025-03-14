package jrw442.Calculator.State;
import jrw442.Calculator.Composite.*;


public interface StateContext {

    public abstract void resetCurrentExpression();

    public abstract Expression getCurrentExpression();

    public abstract void setCurrentExpression(Expression e); 

    public boolean getErrorPopupInput();
    
} 
