package jrw442.Calculator.State;

public class WaitAddSub extends State{

    public WaitAddSub(String currentText) {
        super(currentText);
        super.currentState = "Waiting for add-sub operand";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    // Done
    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            return new GetAddSub(currentText);
        } else if (inputChar == 'C') {
            return new Start("");
        } else {
            return new Error(currentText);
        }
    }

}
