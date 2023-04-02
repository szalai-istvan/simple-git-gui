package classes.listeners;

import classes.commands.ProcessResult;
import classes.components.ResponseLabel;
import classes.util.FileSystemUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.commands.Command.atWorkDirectory;
import static classes.util.Properties.workFolder;

public class DownloadTabDownloadButton implements ActionListener {
    private final ResponseLabel responseLabel;

    public DownloadTabDownloadButton(ResponseLabel responseLabel) {
        this.responseLabel = responseLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProcessResult result = atWorkDirectory().gitClone();
        if (result.isSuccessful()) {
            responseLabel.displaySuccessMessage("Repository cloned successfully!");
            FileSystemUtil.openWithFileManager(workFolder());
        } else {
            responseLabel.displayErrorMessage("Repository cloning failed. Make sure the URL is right, and that git is installed on your system!");
        }
    }
}
