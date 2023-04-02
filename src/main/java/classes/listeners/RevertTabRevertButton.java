package classes.listeners;

import classes.commands.ProcessResult;
import classes.components.ResponseLabel;
import classes.util.Commit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import static classes.commands.Command.atAbsolutePath;
import static classes.util.Properties.repositoryName;
import static classes.util.Properties.workFolder;
import static classes.util.FileSystemUtil.openWithFileManager;

public class RevertTabRevertButton implements ActionListener {

    private final JList<Commit> commits;
    private final ResponseLabel responseLabel;

    public RevertTabRevertButton(JList<Commit> commits, ResponseLabel responseLabel) {
        this.commits = commits;
        this.responseLabel = responseLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Commit selectedValue = commits.getSelectedValue();
        String id = selectedValue.id;
        File folder = Path.of(workFolder(), getFolderName(selectedValue)).toFile();
        if (folder.mkdir()) {
            if (downloadOldCheckout(selectedValue, folder.toPath())) {
                responseLabel.displaySuccessMessage("Old commit checked out successfully!");
            } else {
                responseLabel.displayErrorMessage("An error occured while downloading old version!");
            }
        } else {
            responseLabel.displayErrorMessage("An error occured while downloading old version!");
        }
    }

    private boolean downloadOldCheckout(Commit selectedValue, Path path) {
        ProcessResult cloneResult = atAbsolutePath(path).gitClone();
        if (!cloneResult.isSuccessful()) {
            return false;
        }

        path = path.resolve(repositoryName());
        ProcessResult checkoutResult = atAbsolutePath(path).gitCheckout(selectedValue.id);
        openWithFileManager(path.toString());
        return checkoutResult.isSuccessful() && checkoutResult.isSuccessful();
    }

    private String getFolderName(Commit selectedValue) {
        return selectedValue.message.substring(0, 12) + "_" + selectedValue.id.substring(0, 12);
    }
}
