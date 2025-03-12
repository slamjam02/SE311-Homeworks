package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Observer.StateContext;

public class GetAddSub extends State{

    public GetAddSub(StateContext context) {
        super(context);

        super.currentState = "Getting add-sub operand";
        System.out.println("\nCurrent state: " + super.currentState + "\nCurrent expression: " + super.getCurrentText());
    }
    
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
        Character inputChar = input.charAt(0);

        Expression currentExpression = context.getCurrentExpression();  // Get global expression

        if (Character.isDigit(inputChar)) {
            currentExpression.enterDigit(Character.getNumericValue(inputChar));
            return new GetAddSub(context);
        } 
        else if (inputChar == '*' || inputChar == '/') {
            if (currentExpression instanceof AddSubExpression) {
                AddSubExpression parent = (AddSubExpression) currentExpression;

                // Create a new MulDivExpression and attach it to the right side of `+`
                MulDivExpression newMulDiv = new MulDivExpression(parent.getRight(), null, inputChar);
                parent.setRight(newMulDiv);
                
                context.setCurrentExpression(parent);  // Update global tree
                return new WaitMulDiv(context);
            } else {
                MulDivExpression newMulDiv = new MulDivExpression(currentExpression, null, inputChar);
                context.setCurrentExpression(newMulDiv);  // Ensure global tree is updated
                return new WaitMulDiv(context);
            }
        } 
        else if (inputChar == '+' || inputChar == '-') {
            AddSubExpression newAddSub = new AddSubExpression(currentExpression, null, inputChar);
            context.setCurrentExpression(newAddSub);  // Update global tree
            return new WaitAddSub(context);
        } 
        else if (inputChar == '=') {
            return new Calculate(context);
        } 
        else {
            return new Start(context); // Handle invalid input
        }
    }
}   