package org.pandenutella.game;

import org.pandenutella.game.controller.SnakeController;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.global.framework.GameLoop;
import org.pandenutella.game.global.object.GridManager;
import org.pandenutella.game.object.apple.AppleSpawner;
import org.pandenutella.game.object.snake.Snake;
import org.pandenutella.game.utility.Position;

import java.awt.Dimension;

public class Application {

    private static final int SCREEN_WIDTH = 540;
    private static final int SCREEN_HEIGHT = 540;
    private static final int SNAKE_SIZE = 60;
    private static final int SNAKE_LENGTH = 3;
    private static final double SNAKE_MOVEMENT = 2.0;

    public static void main(String[] args) {
        Dimension screenBounds = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

        Panel.initialize(screenBounds);
        GridManager.initialize(SNAKE_SIZE);
        GameLoop.initialize(SNAKE_MOVEMENT);

        Window.initialize();
        Window.display();

        initializeGameObjects(screenBounds);

        GameLoop.start();
    }

    private static void initializeGameObjects(Dimension screenBounds) {
        // Snake
        Position snakePosition = new Position(getInitialPosition(SCREEN_WIDTH), getInitialPosition(SCREEN_HEIGHT));
        Snake snake = new Snake(SNAKE_SIZE, snakePosition, SNAKE_LENGTH, screenBounds);
        new SnakeController(snake);
        // Apple Spawner
        new AppleSpawner(SNAKE_SIZE, 2);
    }

    private static int getInitialPosition(int screenSize) {
        return (screenSize / 2) - (Application.SNAKE_SIZE / 2);
    }

}
