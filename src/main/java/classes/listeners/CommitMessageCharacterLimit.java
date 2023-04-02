package classes.listeners;

import classes.components.ResponseLabel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommitMessageCharacterLimit implements KeyListener {

    private final JTextArea textArea;
    private final ResponseLabel responseLabel;

    public CommitMessageCharacterLimit(JTextArea textArea, ResponseLabel responseLabel) {
        this.textArea = textArea;
        this.responseLabel = responseLabel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        checkTextArea();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        checkTextArea();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        checkTextArea();
    }

    private void checkTextArea() {
        if (textArea.getText().length() > 72) {
            textArea.setText(textArea.getText().substring(0, 72));
            responseLabel.displayInfo("Git commit messages are limited to 72 characters.");
        }
    }
}
