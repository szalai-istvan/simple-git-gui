package classes.tabdefinitions;

import classes.components.ResponseLabel;
import classes.listeners.RevertTabListListener;
import classes.listeners.RevertTabRefreshButton;
import classes.listeners.RevertTabRevertButton;
import classes.util.Commit;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import static classes.util.Colors.*;
import static classes.util.FormattingUtil.*;

public class RevertTab implements Tabs {

    @Override
    public JPanel buildTab() {
        JPanel tab = Tabs.getBaseTab();

        JList<Commit> commits = new JList<>();
        commits.setSelectionBackground(PINK_227_100_110);
        commits.setListData(Commit.getCommits());
        JLabel dateLabel = new JLabel("Date: ");
        commits.addListSelectionListener(new RevertTabListListener(dateLabel, commits));
        ResponseLabel responseLabel = new ResponseLabel();
        JButton refreshButton = new JButton("Refresh version list");
        JButton revertButton = new JButton("Download version");
        JScrollPane scroll = new JScrollPane(commits);

        revertButton.addActionListener(new RevertTabRevertButton(commits, responseLabel));
        refreshButton.addActionListener(new RevertTabRefreshButton(commits, responseLabel));

        tab.add(formatComponent(dateLabel, 4, 0, 1, 2));
        tab.add(formatComponent(responseLabel, 5, 0, 1, 2));
        tab.add(formatButton(refreshButton, 6, 0, 2, 1));
        tab.add(formatButton(revertButton, 6, 1, 2, 1));
        tab.add(formatScrollPane(scroll, 0, 0, 4, 2));
        return tab;
    }
}
