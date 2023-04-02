package classes.listeners;

import classes.util.Commit;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RevertTabListListener implements ListSelectionListener {
    private final JLabel dateLabel;
    private final JList<Commit> commits;
    public RevertTabListListener(JLabel dateField, JList<Commit> commits) {
        this.dateLabel = dateField;
        this.commits = commits;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        dateLabel.setText("Date: " + commits.getSelectedValue().date);
    }
}
