import javax.swing.*;
import java.io.IOException;

import static classes.tabdefinitions.Tabs.buildTabs;
import static classes.util.Colors.PINK_237_231_232;
import static classes.util.Colors.PINK_240_158_166;
import static classes.util.FileSystemUtil.initializeEnvironment;
import static classes.util.FormattingUtil.addFormatRules;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) throws IOException {
        initializeEnvironment();
        addFormatRules();

        JFrame window = new JFrame("Simple Version Control UI");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setSize(495, 305);
        window.getContentPane().setBackground(PINK_237_231_232);

        JTabbedPane tabbedPane = buildTabs();
        tabbedPane.setBackground(PINK_240_158_166);
        window.add(tabbedPane);
        window.setVisible(true);
    }
}