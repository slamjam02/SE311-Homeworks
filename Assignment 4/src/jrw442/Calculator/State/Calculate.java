package jrw442.Calculator.State;

import jrw442.Calculator.Composite.AtomicExpression;
import jrw442.Calculator.Composite.Expression;
import jrw442.Calculator.Visitor.ParserVisitor;
import jrw442.Calculator.Visitor.SolveVisitor;

public class Calculate extends State{

    private String result;

    public Calculate(StateContext context) {
        super(context);

        // Calculate stuff

        // Open socket
        // Send equation

        SolveVisitor solver = new SolveVisitor();
        this.context.getCurrentExpression().acceptVisitor(solver);

        result = solver.getResultString();

        // Send = + result string
        // Close socket

        super.currentState = "Calculating";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.getCurrentText());

    }

    @Override
    public State getNextState(String input) {


        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);

        if(Character.isDigit(inputChar)){
            return new GetFirstOp(new AtomicExpression(Double.parseDouble(input)));
        } else {
            return new Start();
        }
        
        
    }
    
    @Override
    public String getResult(){
        return this.result;
    }

}