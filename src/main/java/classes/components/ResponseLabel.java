package classes.components;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicLong;

import static classes.util.Colors.BLUE_23_165_187;
import static classes.util.Colors.RED_240_24_139;

public class ResponseLabel extends JLabel {
    private AtomicLong cleaningTime = new AtomicLong(0L);
    private boolean cleaningInProgress = false;

    public ResponseLabel() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    public void displaySuccessMessage(String message) {
        scheduleCleaning(10_000);
        this.setForeground(BLUE_23_165_187);
        this.setText("<html>☑" + message + "</html>");
    }

    public void displayErrorMessage(String message) {
        scheduleCleaning(10_000);
        this.setForeground(RED_240_24_139);
        this.setText("<html>☒" + message + "</html>");
    }

    public void displayInfo(String info) {
        scheduleCleaning(10_000);
        this.setForeground(Color.BLACK);
        this.setText("<html>Info: " + info + "</html>");
    }

    private void scheduleCleaning(long millis) {
        cleaningTime.set(System.currentTimeMillis() + millis);

        if (!cleaningInProgress) {
            new Thread(() -> {
                while (System.currentTimeMillis() < cleaningTime.get()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.setText("");
                this.cleaningInProgress = false;
            }).start();
            this.cleaningInProgress = true;
        }

    }
}
