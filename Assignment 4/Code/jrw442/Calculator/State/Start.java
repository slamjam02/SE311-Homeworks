package jrw442.Calculator.State;


public class Start extends State {

    public Start(String currentText){
        super(currentText);
        super.currentState = "Start";
    }

    @Override
    public State getNextState(String input) {
        super.currentText = currentText + input;
        System.out.println("\nChar pressed: " + input + "\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);
    
        if(Character.isDigit(input.charAt(0))){
            return new GetFirstOp(currentText);
        } else {
            return new Start("");
        }

    }
    
}
