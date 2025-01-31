package editor.listeners;

import editor.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view) {
        this.view = view;
    }



    @Override
    public void menuSelected(MenuEvent e) {
        JMenu item = (JMenu) e.getSource();
        Component[] components = item.getMenuComponents();
        boolean value = view.isHtmlTabSelected();
        for (Component component : components) {
            component.setEnabled(value);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
