package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;
import jrw442.Calculator.Observer.StateContext;

public class GetAddSub extends State {

    public GetAddSub(StateContext context) {
        super(context);
        super.currentState = "Getting add-sub operand";
        System.out.println("\nCurrent state: " + super.currentState 
                + "\nCurrent expression: " + super.getCurrentText());
    }

    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
        char inputChar = input.charAt(0);

        Expression currentExpression = context.getCurrentExpression();

        if (Character.isDigit(inputChar)) {
            currentExpression.enterDigit(Character.getNumericValue(inputChar));
            return this; // stay in GetAddSub
        }
        else if (inputChar == '*' || inputChar == '/') {
            // If we have an AddSubExpression, attach a MulDiv on its right operand
            if (currentExpression instanceof AddSubExpression) {
                AddSubExpression addSub = (AddSubExpression) currentExpression;
                Expression rightSide = addSub.getRight();

                if (rightSide instanceof MulDivExpression) {
                    // chain further into the existing MulDiv chain
                    MulDivExpression mulDiv = (MulDivExpression) rightSide;
                    while (mulDiv.getRight() instanceof MulDivExpression) {
                        mulDiv = (MulDivExpression) mulDiv.getRight();
                    }
                    // Attach new MulDiv to the far right
                    MulDivExpression newMulDiv = 
                          new MulDivExpression(mulDiv.getRight(), null, inputChar);
                    mulDiv.setRight(newMulDiv);
                } 
                else {
                    // rightSide is just a Literal or null, so create a new MulDiv node
                    MulDivExpression newMulDiv = 
                          new MulDivExpression(rightSide, null, inputChar);
                    addSub.setRight(newMulDiv);
                }

                context.setCurrentExpression(addSub);
            } 
            else {
                // If no AddSub yet, just wrap the entire expression in a MulDiv
                MulDivExpression newMulDiv = 
                      new MulDivExpression(currentExpression, null, inputChar);
                context.setCurrentExpression(newMulDiv);
            }

            // Now we want the user to type the next digit for the new MulDiv
            return new WaitMulDiv(context);
        }
        else if (inputChar == '+' || inputChar == '-') {
            // Create or extend an AddSub expression
            AddSubExpression newAddSub = 
                  new AddSubExpression(currentExpression, null, inputChar);
            context.setCurrentExpression(newAddSub);
            return new WaitAddSub(context);
        }
        else if (inputChar == '=') {
            return new Calculate(context);
        }
        else {
            return new Start(context);
        }
    }
}