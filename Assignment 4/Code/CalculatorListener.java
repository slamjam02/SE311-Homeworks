import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CalculatorListener implements ActionListener{
    private State currentState;
    public CalculatorListener(){
        this.currentState = new Start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.currentState = currentState.getNextState(((JButton) e.getSource()).getText());
    }
}
