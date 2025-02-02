package editor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    private HTMLEditorKit kit = new HTMLEditorKit();

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String... args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init() {
        createNewDocument();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("Sigma редактор");
        currentFile = null;

    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) kit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
            kit.read(reader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        try (StringWriter writer = new StringWriter()) {
            kit.write(writer, document, 0, document.getLength());
            return writer.toString();
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void exit() {
        System.exit(0);
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        int option = chooser.showSaveDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                kit.write(fileWriter, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }

    }

    public void saveDocument() {
        if (currentFile == null)
            saveDocumentAs();
        else {
            view.selectHtmlTab();
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                kit.write(fileWriter, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        int option = chooser.showOpenDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            resetDocument();
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile)) {
                kit.read(fileReader, document, 0);
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }
}
