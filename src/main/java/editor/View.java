package editor;

import editor.listeners.FrameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
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

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {

    }
    public void initEditor() {

    }
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }
}