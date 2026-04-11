package org.pandenutella.game.framework;

import org.pandenutella.game.controller.GameController;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public void setGameLoop(GameLoop gameLoop) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameLoop.stop();
            }
        });
    }
}
