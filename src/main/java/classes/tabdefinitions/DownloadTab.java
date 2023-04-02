package classes.tabdefinitions;

import classes.components.ResponseLabel;
import classes.listeners.DownloadTabDownloadButton;
import classes.listeners.DownloadTabUpdateButton;

import javax.swing.*;

import static classes.util.Colors.PINK_244_224_226;
import static classes.util.FormattingUtil.formatButton;
import static classes.util.FormattingUtil.formatComponent;

public class DownloadTab implements Tabs {

    @Override
    public JPanel buildTab() {
        JPanel tab = Tabs.getBaseTab();

        JButton initRepoButton = new JButton("Download repository");
        JButton updateRepoButton = new JButton("Update repository");
        JLabel explanation = new JLabel("<html><p align=\"center\">Use the 'Download repository' button only when initializing your local repo. <br> In any other case, use the 'Update repository' button</p></html>");
        ResponseLabel responseLabel = new ResponseLabel();
        initRepoButton.addActionListener(new DownloadTabDownloadButton(responseLabel));
        updateRepoButton.addActionListener(new DownloadTabUpdateButton(responseLabel));
        tab.add(formatComponent(responseLabel, 4, 0, 2, 2));
        tab.add(formatComponent(explanation, 1, 0, 2, 2));
        tab.add(formatButton(initRepoButton, 6, 0, 2, 1));
        tab.add(formatButton(updateRepoButton, 6, 1, 2, 1));
        return tab;
    }
}
