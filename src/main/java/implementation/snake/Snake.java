package implementation.snake;

import framework.control.Controllable;
import framework.control.Updatable;
import framework.display.Painter;
import implementation.constants.Direction;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static framework.display.PaintUtility.getInstance;
import static implementation.constants.Direction.DOWN;
import static implementation.constants.Direction.LEFT;
import static implementation.constants.Direction.RIGHT;
import static implementation.constants.Direction.UP;
import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;
import static java.util.Arrays.asList;

public class Snake implements Updatable, Painter, Controllable {
    private final SnakeHead snakeHead;
    private final List<SnakeBody> snakeBodyList;

    private final static int BLOCK_SIZE = 30;

    private boolean alive = true;
    private Direction nextDirection = UP;

    public Snake(int x, int y, int length) {
        snakeHead = new SnakeHead();
        snakeHead.setDirection(UP);
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

    @Override
    public void update() {
        if (!alive)
            return;

        for (int i = snakeBodyList.size() - 1; i >= 0; i--) {
            SnakeBody snakeBody = snakeBodyList.get(i);

            if (i == 0) {
                snakeBody.moveTo(snakeHead.getX(), snakeHead.getY());
                continue;
            }

            SnakeBody nextSnakeBody = snakeBodyList.get(i - 1);
            snakeBody.moveTo(nextSnakeBody.getX(), nextSnakeBody.getY());
        }

        if (isNextDirectionValid(snakeHead.getDirection(), nextDirection))
            snakeHead.setDirection(nextDirection);
        else
            nextDirection = snakeHead.getDirection();

        snakeHead.moveTowardsDirection(BLOCK_SIZE);

        boolean collided = false;
        for (SnakeBody body : snakeBodyList) {
            if (!(body.getX() == snakeHead.getX() && body.getY() == snakeHead.getY()))
                continue;

            collided = true;
            break;
        }

        if (collided)
            alive = false;
    }

    @Override
    public void paint(Graphics g) {
        getInstance().fillRect(g, snakeHead.getX(), snakeHead.getY(), BLOCK_SIZE, BLOCK_SIZE, RED);
        snakeBodyList.forEach(snakeBody -> {
            getInstance().fillRect(g, snakeBody.getX(), snakeBody.getY(), BLOCK_SIZE, BLOCK_SIZE, BLACK);
        });
    }

    @Override
    public List<Integer> getListenedKeyCodes() {
        return asList(VK_W, VK_D, VK_S, VK_A);
    }

    @Override
    public void performAction(int keyCode) {
        if (!alive)
            return;

        nextDirection = getDirectionFromKeyCode(keyCode);
    }

    public Direction getDirectionFromKeyCode(int keyCode) {
        return switch (keyCode) {
            case VK_W -> UP;
            case VK_D -> RIGHT;
            case VK_S -> DOWN;
            case VK_A -> LEFT;
            default -> null;
        };
    }

    public boolean isNextDirectionValid(Direction direction, Direction nextDirection) {
        if (direction == UP && nextDirection != DOWN)
            return true;
        else if (direction == RIGHT && nextDirection != LEFT)
            return true;
        else if (direction == DOWN && nextDirection != UP)
            return true;
        else return direction == LEFT && nextDirection != RIGHT;
    }

    public boolean isAlive() {
        return alive;
    }
}
