package org.pandenutella.game;

import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.controller.PlayerController;
import org.pandenutella.game.framework.GameLoop;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.Snake;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

public class Application {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SNAKE_SIZE = 30;
    private static final int SNAKE_LENGTH = 3;
    private static final double SNAKE_MOVEMENT = 2.0;

    public static void main(String[] args) {
        int snakeX = getInitialPosition(SCREEN_WIDTH);
        int snakeY = getInitialPosition(SCREEN_HEIGHT);

        Snake snake = new Snake(SNAKE_SIZE, snakeX, snakeY, SNAKE_LENGTH);

        GameController playerController = new PlayerController(snake);

        List<GameController> controllerList = Collections.singletonList(playerController);
        List<GameObject> gameObjectList = Collections.singletonList(snake);

        Dimension screenSize = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        Panel panel = new Panel(screenSize, gameObjectList);

        Window window = new Window(panel, controllerList);
        window.display();

        GameLoop gameLoop = new GameLoop(SNAKE_MOVEMENT, panel, controllerList, gameObjectList);
        gameLoop.start();
    }

    private static int getInitialPosition(int screenSize) {
        return (screenSize / 2) - (Application.SNAKE_SIZE / 2);
    }

}
