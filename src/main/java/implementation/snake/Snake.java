package implementation.snake;

import framework.display.Painter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static framework.display.PaintUtility.getInstance;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;

public class Snake implements Painter {
    private final SnakeHead snakeHead;
    private final List<SnakeBody> snakeBodyList;

    private final static int BLOCK_SIZE = 30;

    public Snake(int x, int y, int length) {
        snakeHead = new SnakeHead();
        snakeHead.setX(x * BLOCK_SIZE);
        snakeHead.setY(y * BLOCK_SIZE);

        snakeBodyList = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            SnakeBody snakeBody = new SnakeBody();
            snakeBody.setX(x * BLOCK_SIZE);
            snakeBody.setY((y + i) * BLOCK_SIZE);

            snakeBodyList.add(snakeBody);
        }
    }

    public void update() {
    }

    @Override
    public void paint(Graphics g) {
        getInstance().fillRect(g, snakeHead.getX(), snakeHead.getY(), BLOCK_SIZE, BLOCK_SIZE, RED);
        snakeBodyList.forEach(snakeBody -> {
            getInstance().fillRect(g, snakeBody.getX(), snakeBody.getY(), BLOCK_SIZE, BLOCK_SIZE, WHITE);
        });
    }
}
