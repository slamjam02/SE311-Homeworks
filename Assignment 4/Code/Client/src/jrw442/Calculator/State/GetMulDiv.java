package jrw442.Calculator.State;

import jrw442.Calculator.Composite.*;

public class GetMulDiv extends State {

    public GetMulDiv(StateContext context) {
        super(context);
        super.currentState = "Getting mul-div operand";
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
            return this; // stay in GetMulDiv
        }
        else if (inputChar == '*' || inputChar == '/') {
            // If the current expression is already MulDiv, chain at the rightmost node
            if (currentExpression instanceof MulDivExpression) {
                MulDivExpression mulDiv = (MulDivExpression) currentExpression;
                while (mulDiv.getRight() instanceof MulDivExpression) {
                    mulDiv = (MulDivExpression) mulDiv.getRight();
                }
                MulDivExpression newMulDiv = 
                      new MulDivExpression(mulDiv.getRight(), null, inputChar);
                mulDiv.setRight(newMulDiv);

                context.setCurrentExpression(mulDiv); 
            }
            // If the current expression is an AddSub, then attach to the right operand 
            else if (currentExpression instanceof AddSubExpression) {
                AddSubExpression addSub = (AddSubExpression) currentExpression;
                Expression rightSide = addSub.getRight();

                // If that right side is MulDiv, chain further
                if (rightSide instanceof MulDivExpression) {
                    MulDivExpression mulDiv = (MulDivExpression) rightSide;
                    while (mulDiv.getRight() instanceof MulDivExpression) {
                        mulDiv = (MulDivExpression) mulDiv.getRight();
                    }
                    MulDivExpression newMulDiv = 
                          new MulDivExpression(mulDiv.getRight(), null, inputChar);
                    mulDiv.setRight(newMulDiv);
                } 
                else {
                    // otherwise create a new MulDiv for that side
                    MulDivExpression newMulDiv = 
                          new MulDivExpression(rightSide, null, inputChar);
                    addSub.setRight(newMulDiv);
                }

                context.setCurrentExpression(addSub);
            }
            else {
                // Otherwise, wrap the entire expression in a new MulDiv
                MulDivExpression newMulDiv = 
                      new MulDivExpression(currentExpression, null, inputChar);
                context.setCurrentExpression(newMulDiv);
            }

            return new WaitMulDiv(context);
        }
        else if (inputChar == '+' || inputChar == '-') {
            // Next operator is AddSub, so we wrap the entire current mul/div chain
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