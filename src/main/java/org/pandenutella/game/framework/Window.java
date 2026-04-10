package org.pandenutella.game.framework;

import org.pandenutella.game.controller.GameController;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.util.List;

public class Window {

    private final JFrame frame;
    private final Panel panel;
    private final List<GameController> gameControllerList;

    public Window(Panel panel, List<GameController> gameControllerList) {
        this.frame = new JFrame();

        this.panel = panel;
        this.gameControllerList = gameControllerList;

        this.setup();
    }

    public void setup() {
        frame.setTitle("Snake [Pan de Nutella]");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().add(panel);
        gameControllerList.forEach(frame::addKeyListener);
    }

    public void display() {
        frame.setVisible(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
    }
}
