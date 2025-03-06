package jrw442.Calculator.State;

public class WaitAddSub extends State{

    public WaitAddSub(String currentText) {
        super(currentText);
        super.currentState = "Waiting for add-sub operand";
    }

    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input + "\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);
    
        Character inputChar = input.charAt(0);
        if (Character.isDigit(inputChar)){
            return new GetAddSub(currentText);
        } else {
            return new Error(currentText);
        }
    }

}
