package jrw442.Calculator.State;

public class Calculate extends State{

    public Calculate(String currentText) {

        // Calculate stuff

        super("calculated formula here");
        super.currentState = "Calculating";


        System.out.println("\nCurrent state: " + super.currentState +  "\nCurrent string: " + super.currentText);

    }

    @Override
    public State getNextState(String input) {


        System.out.println("\nChar pressed: " + input);
    
        //Character inputChar = input.charAt(0);

        return new Start("");
        
    }

}