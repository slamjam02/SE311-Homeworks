package jrw442.Calculator.State;

import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Visitor.ParserVisitor;
import jrw442.Calculator.Visitor.SolveVisitor;

public class Calculate extends State{

    public Calculate(String currentText) {
        super(currentText);

        // Calculate stuff

        ParserVisitor expressionHandler = new ParserVisitor();
        Expression expression = expressionHandler.parse(currentText);
        SolveVisitor solver = new SolveVisitor();
        expression.acceptVisitor(solver);

        super.currentText = solver.getResultString();
        super.currentState = "Calculating";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    @Override
    public State getNextState(String input) {


        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);

        if(Character.isDigit(inputChar)){
            return new Start(input);
        } else {
            return new Start("");
        }
        
        
    }

}