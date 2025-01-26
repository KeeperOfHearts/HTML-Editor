package editor.action;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super("Надстрочный знак");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}