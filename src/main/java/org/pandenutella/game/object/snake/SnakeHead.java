package org.pandenutella.game.object.snake;

import lombok.Getter;
import org.pandenutella.game.constant.Direction;
import org.pandenutella.game.utility.Position;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class SnakeHead {

    private final int size;
    private final Dimension screenBounds;

    @Getter
    private final Position position;

    public SnakeHead(int size, Position position, Dimension screenBounds) {
        this.size = size;
        this.position = position;
        this.screenBounds = screenBounds;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(position.getX(), position.getY(), size, size);
    }

    public void moveTowards(Direction direction) {
        switch (direction) {
            case UP:
                position.offsetY(-size);
                if (position.getY() < 0) {
                    position.offsetY(screenBounds.height);
                }
                break;
            case RIGHT:
                position.offsetX(size);
                if (position.getX() > screenBounds.width - size) {
                    position.offsetX(-screenBounds.width);
                }
                break;
            case DOWN:
                position.offsetY(size);
                if (position.getY() > screenBounds.height - size) {
                    position.offsetY(-screenBounds.height);
                }
                break;
            case LEFT:
                position.offsetX(-size);
                if (position.getX() < 0) {
                    position.offsetX(screenBounds.width);
                }
                break;
        }
    }
}
