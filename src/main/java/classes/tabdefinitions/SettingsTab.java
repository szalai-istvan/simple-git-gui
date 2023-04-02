package classes.tabdefinitions;

import classes.components.ResponseLabel;
import classes.listeners.SettingsTabSaveButton;
import classes.util.Properties;

import javax.swing.*;

import static classes.util.Colors.PINK_244_224_226;
import static classes.util.FormattingUtil.formatButton;
import static classes.util.FormattingUtil.formatComponent;
import static classes.util.Properties.*;

public class SettingsTab implements Tabs {

    @Override
    public JPanel buildTab() {
        JPanel tab = Tabs.getBaseTab();

        JTextField workfolder = new JTextField(workFolder());
        JTextField url = new JTextField(remoteUrl());
        JPasswordField tokenField = new JPasswordField(token());
        JButton saveButton = new JButton("Save settings");
        ResponseLabel responseLabel = new ResponseLabel();
        responseLabel.setHorizontalAlignment(JLabel.CENTER);
        responseLabel.setVerticalAlignment(JLabel.CENTER);

        saveButton.addActionListener(new SettingsTabSaveButton(workfolder, url, tokenField, responseLabel));

        tab.add(formatComponent(new JLabel("Workfolder:"), 0, 0));
        tab.add(formatComponent(workfolder, 0, 1));
        tab.add(formatComponent(new JLabel("Repository URL:"), 1, 0));
        tab.add(formatComponent(url, 1, 1));
        tab.add(formatComponent(new JLabel("Access token:"), 2, 0));
        tab.add(formatComponent(tokenField, 2, 1));
        tab.add(formatButton(saveButton, 6, 0, 2, 2));
        tab.add(formatComponent(responseLabel, 4, 0, 2, 2));
        return tab;
    }
}
