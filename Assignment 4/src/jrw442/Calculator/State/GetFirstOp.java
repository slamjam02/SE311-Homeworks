package jrw442.Calculator.State;

public class GetFirstOp extends State{

    public GetFirstOp(String currentText) {
        super(currentText);
        super.currentState = "Getting first operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);
    }

    // Done
    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            return new GetFirstOp(currentText);
        } else if (inputChar == '+' || inputChar == '-'){
            return new WaitAddSub(currentText);
        } else if (inputChar == '*' || inputChar == '/'){
            return new WaitMulDiv(currentText);
        } else if (inputChar == '='){
            return new Calculate(currentText);
        } else {
            return new Start("");
        }
    }

}
