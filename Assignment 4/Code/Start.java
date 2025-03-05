
public class Start extends State {

    @Override
    public State getNextState(String input) {
        // TODO Auto-generated method stub
        System.out.println("Pressed: " + input);
        return new Start();
    }
    
}
