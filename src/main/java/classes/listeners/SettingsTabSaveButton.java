package classes.listeners;

import classes.components.ResponseLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static classes.util.Properties.*;
import static java.lang.System.setProperty;
import static classes.util.FileSystemUtil.updateSettingsFile;

public class SettingsTabSaveButton implements ActionListener {

    private final JTextField workfolder;
    private final JTextField url;
    private final ResponseLabel responseLabel;
    private final JPasswordField passwordField;

    public SettingsTabSaveButton(JTextField workfolder, JTextField url, JPasswordField passwordField, ResponseLabel responseLabel) {
        this.workfolder = workfolder;
        this.url = url;
        this.responseLabel = responseLabel;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String folderValue = workfolder.getText();
        String urlValue = url.getText();
        String token = String.copyValueOf(passwordField.getPassword());
        if (!new File(folderValue).exists()) {
            responseLabel.displayErrorMessage("Folder not found! Save failed!");
            return;
        }

        runUpdate(WORK_FOLDER, folderValue);
        runUpdate(REMOTE_URL, urlValue);
        runUpdate(TOKEN, token);

        String[] urlSplit = urlValue.split("/");
        if (urlSplit.length <= 4) {
            responseLabel.displayErrorMessage("Invalid repository URL format.");
            return;
        }

        String userName = urlSplit[3];
        String repositoryName = urlSplit[4].substring(0, urlSplit[4].length() - 4);
        runUpdate(USERNAME, userName);
        runUpdate(REPOSITORY_NAME, repositoryName);

        responseLabel.displaySuccessMessage("Save successful!");
    }

    private void runUpdate(String key, String value) {
        setProperty(key, value);
        updateSettingsFile(key, value);
    }
}
