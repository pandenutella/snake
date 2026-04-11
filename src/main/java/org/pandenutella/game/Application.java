package org.pandenutella.game;

import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.controller.PlayerController;
import org.pandenutella.game.framework.GameLoop;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.apple.AppleSpawner;
import org.pandenutella.game.object.snake.Snake;
import org.pandenutella.game.utility.Position;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.pandenutella.game.manager.GridManager.getGlobalGridManager;

public class Application {

    private static final int SCREEN_WIDTH = 540;
    private static final int SCREEN_HEIGHT = 540;
    private static final int SNAKE_SIZE = 60;
    private static final int SNAKE_LENGTH = 3;
    private static final double SNAKE_MOVEMENT = 2.0;

    public static void main(String[] args) {
        Position snakePosition = new Position(getInitialPosition(SCREEN_WIDTH), getInitialPosition(SCREEN_HEIGHT));
        Dimension screenBounds = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

        Snake snake = new Snake(SNAKE_SIZE, snakePosition, SNAKE_LENGTH, screenBounds);
        AppleSpawner appleSpawner = new AppleSpawner(SNAKE_SIZE, 2);

        GameController playerController = new PlayerController(snake);

        List<GameController> controllerList = Collections.singletonList(playerController);
        List<GameObject> gameObjectList = Arrays.asList(snake, appleSpawner);

        Panel panel = new Panel(screenBounds, gameObjectList);

        Window window = new Window(panel, controllerList);
        window.display();

        getGlobalGridManager().initializeScreenPositionList(screenBounds, SNAKE_SIZE);

        GameLoop gameLoop = new GameLoop(SNAKE_MOVEMENT, panel, controllerList, gameObjectList);
        gameLoop.start();

        window.setGameLoop(gameLoop);
    }

    private static int getInitialPosition(int screenSize) {
        return (screenSize / 2) - (Application.SNAKE_SIZE / 2);
    }

}
