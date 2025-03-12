package jrw442.Calculator.State;

import jrw442.Calculator.Observer.StateContext;

public class WaitMulDiv extends State{

    public WaitMulDiv(StateContext context) {
        super(context);
        super.currentState = "Waiting for mul-div operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            context.getCurrentExpression().enterDigit(Integer.parseInt(input));
            return new GetMulDiv(context);
        } else if (inputChar == '+' || inputChar == '-'){
            return new Error(context, this);
        } else if (inputChar == '*' || inputChar == '/'){
            return new Error(context, this);
        } else if (inputChar == '='){
            return new Error(context, this);
        } else {
            return new Start(context);
        }
    }
 

}