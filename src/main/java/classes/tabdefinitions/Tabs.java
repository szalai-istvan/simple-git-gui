package classes.tabdefinitions;

import javax.swing.*;

import static classes.util.Colors.PINK_244_224_226;

public interface Tabs {

    public static JTabbedPane buildTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Settings", new SettingsTab().buildTab());
        tabbedPane.addTab("Download", new DownloadTab().buildTab());
        tabbedPane.addTab("Upload", new CommitAndPushTab().buildTab());
        tabbedPane.addTab("Revert", new RevertTab().buildTab());
        return tabbedPane;
    }

    public static JPanel getBaseTab() {
        JPanel tab = new JPanel();
        tab.setLayout(null);
        tab.setBackground(PINK_244_224_226);
        return tab;
    }
    JPanel buildTab();
}
