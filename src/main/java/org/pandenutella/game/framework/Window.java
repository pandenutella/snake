package org.pandenutella.game.framework;

import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.global.framework.GameLoop;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    private static Window INSTANCE;

    public static void initialize() {
        INSTANCE = new Window(Panel.getInstance());
        INSTANCE.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameLoop.stop();
            }
        });
    }

    public static Window getInstance() {
        return INSTANCE;
    }

    public static void display() {
        INSTANCE.frame.setVisible(true);
        INSTANCE.frame.pack();
        INSTANCE.frame.setLocationRelativeTo(null);
    }

    private final JFrame frame = new JFrame();
    private final Panel panel;

    public Window(Panel panel) {
        this.panel = panel;
        this.setup();
    }

    private void setup() {
        frame.setTitle("Snake [Pan de Nutella]");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
    }

    public void addGameController(GameController gameController) {
        frame.addKeyListener(gameController);
    }
}
