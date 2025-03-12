package jrw442.Calculator.State;

public class WaitAddSub extends State{

    public WaitAddSub(StateContext context) {
        super(context);
        super.currentState = "Waiting for add-sub operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent expression: " + super.getCurrentText());

    }

    // Done
    @Override
    public State getNextState(String input) {
        System.out.println("Char pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            context.getCurrentExpression().enterDigit(Integer.parseInt(input));
            return new GetAddSub(context);
        } else if (inputChar == 'C') {
            return new Start(context);
        } else {
            return new Error(context, this);
        }

    }

}
