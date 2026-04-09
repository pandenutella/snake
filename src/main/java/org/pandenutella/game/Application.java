package org.pandenutella.game;

import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;

import java.awt.Dimension;

public class Application {

    public static void main(String[] args) {
        Dimension screenSize = new Dimension(400, 400);
        
        Panel panel = new Panel();
        panel.setup(screenSize);

        Window window = new Window();
        window.setup(panel);
        window.display();
    }

}
