import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    public Controller getController() {
        return this.controller;
    }

    public void setController(Controller cont) {
        this.controller = cont;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}