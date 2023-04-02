package classes.listeners;

import classes.commands.Command;
import classes.commands.ProcessResult;
import classes.components.ResponseLabel;
import classes.util.FileSystemUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.util.Properties.workFolder;

public class DownloadTabUpdateButton implements ActionListener {
    private final ResponseLabel responseLabel;

    public DownloadTabUpdateButton(ResponseLabel responseLabel) {
        this.responseLabel = responseLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProcessResult result = Command.inRepository().gitFetch();
        if (result.isSuccessful()) {
            responseLabel.displaySuccessMessage("Repository updated successfully!");
            FileSystemUtil.openWithFileManager(workFolder());
        } else {
            responseLabel.displayErrorMessage("Repository updating failed. Make sure the URL is right, the repository is initiated with the Download button, and that git is installed on your system!");
        }
    }
}
