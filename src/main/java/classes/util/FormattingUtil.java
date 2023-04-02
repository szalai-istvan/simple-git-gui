package classes.util;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;

import static classes.util.Colors.PINK_227_100_110;
import static classes.util.Colors.PINK_237_231_232;

public class FormattingUtil {
    private static final int COMPONENT_WIDTH = 240;
    private static final int COMPONENT_HEIGHT = 25;
    private static final int HGAP = 5;
    private static final int VGAP = 5;

    public static JComponent formatComponent(JComponent component, int row, int column, int rowspan, int colspan) {
        component.setSize(COMPONENT_WIDTH * colspan + (colspan - 1) * VGAP,
                COMPONENT_HEIGHT * rowspan + (rowspan - 1) * HGAP);
        component.setLocation(
                column * COMPONENT_WIDTH + (column + 1) * VGAP,
                row * COMPONENT_HEIGHT + (row + 1) * HGAP
        );

        return component;
    }

    public static JComponent formatComponent(JComponent component, int row, int column) {
        return formatComponent(component, row, column, 1, 1);
    }

    public static JButton formatButton(JButton button, int row, int column, int rowspan, int columnspan) {
        formatComponent(button, row, column, rowspan, columnspan);
        button.setBackground(PINK_227_100_110);
        return button;
    }

    public static JScrollPane formatScrollPane(JScrollPane scroll, int row, int column, int rowspan, int columnspan) {
        formatComponent(scroll, row, column, rowspan, columnspan);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = PINK_227_100_110;
                this.trackColor = PINK_237_231_232;
            }
        });
        return scroll;
    }

    public static void addFormatRules() {
        UIManager.put("TabbedPane.selected", PINK_227_100_110);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
    }
}
