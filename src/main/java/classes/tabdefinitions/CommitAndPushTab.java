package classes.tabdefinitions;

import classes.components.ResponseLabel;
import classes.listeners.CommitMessageCharacterLimit;
import classes.listeners.UploadTabUploadButton;

import javax.swing.*;

import static javax.swing.ScrollPaneConstants.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static classes.util.Colors.PINK_244_224_226;
import static classes.util.FormattingUtil.formatButton;
import static classes.util.FormattingUtil.formatComponent;

public class CommitAndPushTab implements Tabs {

    @Override
    public JPanel buildTab() {
        JPanel tab = Tabs.getBaseTab();

        JLabel commentLabel = new JLabel("Comment: ");
        JTextArea commentArea = new JTextArea();
        commentArea.setLineWrap(true);
        ResponseLabel responseLabel = new ResponseLabel();
        commentArea.addKeyListener(new CommitMessageCharacterLimit(commentArea, responseLabel));
        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new UploadTabUploadButton(responseLabel, commentArea));
        JScrollPane scrollPane = new JScrollPane(commentArea, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tab.add(scrollPane);
        tab.add(formatComponent(commentLabel, 0, 0));
        tab.add(formatComponent(commentArea, 1, 0, 3, 2));
        tab.add(formatComponent(responseLabel, 4, 0, 2, 2));
        tab.add(formatButton(uploadButton, 6, 0, 2, 2));

        return tab;
    }
}
