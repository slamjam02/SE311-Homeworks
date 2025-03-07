package jrw442.Calculator.State;

public class WaitMulDiv extends State{

    public WaitMulDiv(String currentText) {
        super(currentText);
        super.currentState = "Waiting for mul-div operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    // Done
    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            return new GetMulDiv(currentText);
        } else if (inputChar == '+' || inputChar == '-'){
            return new Error(currentText);
        } else if (inputChar == '*' || inputChar == '/'){
            return new Error(currentText);
        } else if (inputChar == '='){
            return new Error(currentText);
        } else {
            return new Start("");
        }
    }
 

}