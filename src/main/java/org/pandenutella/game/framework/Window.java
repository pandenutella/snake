package org.pandenutella.game.framework;

import lombok.RequiredArgsConstructor;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@RequiredArgsConstructor
public class Window {

    private final JFrame frame = new JFrame();

    public void setup(Panel panel) {
        frame.setTitle("Snake [Pan de Nutella]");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().add(panel);
    }

    public void display() {
        frame.setVisible(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
    }
}
