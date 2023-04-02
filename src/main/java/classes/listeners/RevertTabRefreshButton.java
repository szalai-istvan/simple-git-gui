package classes.listeners;

import classes.components.ResponseLabel;
import classes.util.Commit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static classes.util.Commit.getCommits;

public class RevertTabRefreshButton implements ActionListener {
    private final JList<Commit> commits;
    private final ResponseLabel responseLabel;

    public RevertTabRefreshButton(JList<Commit> commits, ResponseLabel responseLabel) {
        this.commits = commits;
        this.responseLabel = responseLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            commits.setListData(getCommits());
            responseLabel.displaySuccessMessage("Commit list refreshed successfully!");
        } catch (Throwable t) {
            responseLabel.displayErrorMessage("An error occured while refreshing commit list!");
        }

    }
}