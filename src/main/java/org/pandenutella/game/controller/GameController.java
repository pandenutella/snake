package org.pandenutella.game.controller;

import lombok.RequiredArgsConstructor;
import org.pandenutella.game.constant.Direction;
import org.pandenutella.game.object.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.LinkedList;

@RequiredArgsConstructor
public class GameController implements KeyListener {

    private final Deque<Direction> directionDeque = new LinkedList<>();
    private final Snake snake;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = getDirection(e.getKeyCode());
        if (direction == null) {
            return;
        }

        directionDeque.remove(direction);
        directionDeque.addFirst(direction);

        snake.face(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Direction direction = getDirection(e.getKeyCode());
        if (direction == null) {
            return;
        }

        directionDeque.remove(direction);

        if (directionDeque.isEmpty()) {
            return;
        }

        Direction nextDirection = directionDeque.getFirst();
        snake.face(nextDirection);
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
