package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Visitor.ExpressionParser;
import jrw442.Calculator.Visitor.SolveVisitor;

public class Calculate extends State{

    public Calculate(String currentText) {
        super(currentText);

        // Calculate stuff

        ExpressionParser expressionHandler = new ExpressionParser();
        Expression expression = expressionHandler.parse(currentText);
        SolveVisitor solver = new SolveVisitor();
        expression.acceptVisitor(solver);

        super.currentText = Double.toString(solver.getResult());
        super.currentState = "Calculating";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    @Override
    public State getNextState(String input) {


        System.out.println("\nChar pressed: " + input);
    
        //Character inputChar = input.charAt(0);

        return new Start("");
        
    }

}