package implementation.entity.snake;

import framework.control.Controllable;
import framework.control.Updatable;
import framework.display.Painter;
import implementation.constants.Direction;
import implementation.entity.apple.Apple;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static framework.display.PaintUtility.getInstance;
import static implementation.constants.Direction.DOWN;
import static implementation.constants.Direction.LEFT;
import static implementation.constants.Direction.RIGHT;
import static implementation.constants.Direction.UP;
import static implementation.constants.GameConstants.BLOCK_SIZE;
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

    private boolean alive = true;
    private boolean willGrow = false;
    private Direction nextDirection = UP;

    public Snake(int col, int row, int length) {
        snakeHead = new SnakeHead();
        snakeHead.setDirection(UP);
        snakeHead.setCol(col);
        snakeHead.setRow(row);

        snakeBodyList = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            SnakeBody snakeBody = new SnakeBody();
            snakeBody.setCol(col);
            snakeBody.setRow((row + i));

            snakeBodyList.add(snakeBody);
        }
    }

    @Override
    public void update() {
        if (!alive)
            return;

        if (willGrow) {
            SnakeBody tail = snakeBodyList.get(snakeBodyList.size() - 1);

            SnakeBody snakeBody = new SnakeBody();
            snakeBody.setCol(tail.getCol());
            snakeBody.setRow(tail.getRow());

            snakeBodyList.add(snakeBody);
        }

        int tailIndex = snakeBodyList.size() - (willGrow ? 2 : 1);
        for (int i = tailIndex; i >= 0; i--) {
            SnakeBody snakeBody = snakeBodyList.get(i);

            if (i == 0) {
                snakeBody.moveTo(snakeHead.getCol(), snakeHead.getRow());
                continue;
            }

            SnakeBody nextSnakeBody = snakeBodyList.get(i - 1);
            snakeBody.moveTo(nextSnakeBody.getCol(), nextSnakeBody.getRow());
        }

        willGrow = false;

        if (isNextDirectionValid(snakeHead.getDirection(), nextDirection))
            snakeHead.setDirection(nextDirection);
        else
            nextDirection = snakeHead.getDirection();

        snakeHead.moveTowardsDirection();

        boolean collided = false;
        for (SnakeBody body : snakeBodyList) {
            if (!(body.getCol() == snakeHead.getCol() && body.getRow() == snakeHead.getRow()))
                continue;

            collided = true;
            break;
        }

        if (collided)
            alive = false;
    }

    @Override
    public void paint(Graphics g) {
        getInstance().fillRect(g, snakeHead.getCol() * BLOCK_SIZE, snakeHead.getRow() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, RED);
        snakeBodyList.forEach(snakeBody -> {
            getInstance().fillRect(g, snakeBody.getCol() * BLOCK_SIZE, snakeBody.getRow() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, BLACK);
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

    public boolean canEatApple(Apple apple) {
        return apple.getCol() == snakeHead.getCol() && apple.getRow() == snakeHead.getRow();
    }

    public void grow() {
        willGrow = true;
    }

    private Direction getDirectionFromKeyCode(int keyCode) {
        return switch (keyCode) {
            case VK_W -> UP;
            case VK_D -> RIGHT;
            case VK_S -> DOWN;
            case VK_A -> LEFT;
            default -> null;
        };
    }

    private boolean isNextDirectionValid(Direction direction, Direction nextDirection) {
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
