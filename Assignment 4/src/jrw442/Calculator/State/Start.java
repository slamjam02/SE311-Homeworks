package jrw442.Calculator.State;


public class Start extends State {

    public Start(String currentText){
        super(currentText);
        super.currentState = "Start";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    // Done
    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input);
    
        if(Character.isDigit(input.charAt(0))){
            return new GetFirstOp(currentText);
        } else {
            return new Start("");
        }

    }
    
}
