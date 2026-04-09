package org.pandenutella.game;

import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.framework.GameLoop;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.Snake;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Snake snake = new Snake(30, 185, 185, 3);
        List<GameObject> gameObjectList = Collections.singletonList(snake);

        GameController gameController = new GameController(snake);
        List<KeyListener> keyListenerList = Collections.singletonList(gameController);

        Dimension screenSize = new Dimension(400, 400);
        Panel panel = new Panel(screenSize, gameObjectList);

        Window window = new Window(panel, keyListenerList);
        window.display();

        GameLoop gameLoop = new GameLoop(panel, gameObjectList);
        gameLoop.start();
    }

}
