package jrw442.Calculator.State;

public class Error extends State{

    public Error(String currentText){
        super("Err");
        super.currentState = "Error";

        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    @Override
    public State getNextState(String input) {
        System.out.println("\nChar pressed: " + input);
    
        Character inputChar = input.charAt(0);
        if (inputChar == 'C'){
            return new Start("");
        } else {
            return new Error(currentText);
        }
    }

}