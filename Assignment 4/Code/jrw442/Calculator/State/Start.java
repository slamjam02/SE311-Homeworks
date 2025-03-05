package jrw442.Calculator.State;


public class Start extends State {

    private String currentText;

    public Start(String currentText){
        this.currentText = currentText;
    }

    @Override
    public State getNextState(String input) {
        // TODO Auto-generated method stub
        System.out.println("Pressed: " + input);
        this.currentText = currentText + input;
        return new Start(currentText);
    }

    @Override
    public String getDisplayText() {
        return this.currentText;
    }
    
}
