package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FixedSizeGame {
    private JFrame frame;
    JPanel panel;

    public FixedSizeGame(int width, int height, String title) {
        var size = new Dimension(width, height);

        this.panel = Globals.ph;
        panel.setPreferredSize(size);
        panel.setMinimumSize(size);
        panel.setMaximumSize(size);
        panel.addMouseListener(Globals.ml);
        panel.addMouseMotionListener(Globals.ml);


        frame = new JFrame(title);
        frame.setBackground(Color.BLACK);
        frame.addKeyListener(Globals.kl);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                Globals.ge.setRunning(false);
                dispose();
            }
        });
    }

    public void dispose() {
        frame.dispose();
    }
}
