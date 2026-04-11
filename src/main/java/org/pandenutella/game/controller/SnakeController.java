package org.pandenutella.game.controller;

import org.pandenutella.game.constant.Direction;
import org.pandenutella.game.object.DirectionControllable;

import java.awt.event.KeyEvent;
import java.util.Deque;
import java.util.LinkedList;

public class SnakeController extends GameControllerImpl {

    private final DirectionControllable directionControllable;
    private final Deque<Direction> movementDeque = new LinkedList<>();

    public SnakeController(DirectionControllable directionControllable) {
        super();
        this.directionControllable = directionControllable;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = getDirection(e.getKeyCode());
        if (direction == null) {
            return;
        }

        movementDeque.remove(direction);
        movementDeque.addFirst(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void update() {
        Direction nextDirection = getNextDirection();
        directionControllable.setDirection(nextDirection);
    }

    private Direction getNextDirection() {
        Direction currentDirection = directionControllable.getDirection();

        for (Direction direction : movementDeque) {
            if (currentDirection == direction || currentDirection.isAdjacent(direction)) {
                return direction;
            }
        }

        return currentDirection;
    }

    private Direction getDirection(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.VK_UP -> Direction.UP;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            case KeyEvent.VK_DOWN -> Direction.DOWN;
            case KeyEvent.VK_LEFT -> Direction.LEFT;
            default -> null;
        };
    }
}
