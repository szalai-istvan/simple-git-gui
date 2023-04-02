package classes.listeners;

import classes.commands.ProcessResult;
import classes.components.ResponseLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static classes.commands.Command.*;
import static classes.util.Properties.*;

public class UploadTabUploadButton implements ActionListener {

    private final ResponseLabel responseLabel;
    private final JTextArea commitMessage;

    public UploadTabUploadButton(ResponseLabel responseLabel, JTextArea jTextArea) {
        this.responseLabel = responseLabel;
        this.commitMessage = jTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProcessResult statusResult = inRepository().gitStatus();
        List<String> output = statusResult.getOutput();
        boolean nothingToCommit = output.stream().anyMatch(l -> l.contains("nothing to commit"));
        if (nothingToCommit) {
            responseLabel.displayInfo("Nothing to commit now");
            return;
        }

        if (commitMessage.getText() == null || commitMessage.getText().length() == 0) {
            responseLabel.displayErrorMessage("Please add a comment to your commit!");
            return;
        }

        ProcessResult pushResult = inRepository().commitAndPush(commitMessage.getText());
        if (pushResult.isSuccessful()) {
            responseLabel.displaySuccessMessage("Upload successful!");
        } else {
            responseLabel.displayErrorMessage(getErrorMessage(pushResult.getOutput().get(0)));
        }
    }

    private String getErrorMessage(String prompt) {
        String password = token();
        return String.format("An error occured on '%s'", prompt)
                .replace(password, toStars(password));
    }

    private String toStars(String password) {
        StringBuilder builder = new StringBuilder();
        for (char c : password.toCharArray()) {
            builder.append('*');
        }
        return builder.toString();
    }

}
