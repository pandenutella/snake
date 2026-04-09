package org.pandenutella.game.framework;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyListener;
import java.util.List;

public class Window {

    private final JFrame frame;
    private final Panel panel;
    private final List<KeyListener> keyListenerList;

    public Window(Panel panel, List<KeyListener> keyListenerList) {
        this.frame = new JFrame();

        this.panel = panel;
        this.keyListenerList = keyListenerList;

        this.setup();
    }

    public void setup() {
        frame.setTitle("Snake [Pan de Nutella]");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().add(panel);
        keyListenerList.forEach(frame::addKeyListener);
    }

    public void display() {
        frame.setVisible(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
    }
}
