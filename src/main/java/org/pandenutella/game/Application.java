package org.pandenutella.game;

import org.pandenutella.game.framework.GameLoop;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.GameObject;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<GameObject> gameObjectList = Collections.emptyList();

        Dimension screenSize = new Dimension(400, 400);
        Panel panel = new Panel(screenSize, gameObjectList);

        Window window = new Window(panel);
        window.display();

        GameLoop gameLoop = new GameLoop(panel, gameObjectList);
        gameLoop.start();
    }

}
