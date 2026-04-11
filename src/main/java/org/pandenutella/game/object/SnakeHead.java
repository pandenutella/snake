package org.pandenutella.game.object;

import lombok.Getter;
import lombok.Setter;
import org.pandenutella.game.constant.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class SnakeHead implements GameObject {

    private final int size;
    private final Dimension screenBounds;

    @Getter
    @Setter
    private int x;

    @Getter
    @Setter
    private int y;

    public SnakeHead(int size, int x, int y, Dimension screenBounds) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.screenBounds = screenBounds;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
    }

    public void moveTowards(Direction direction) {
        switch (direction) {
            case UP:
                y -= size;
                if (y <= 0) {
                    y += screenBounds.height;
                }
                break;
            case RIGHT:
                x += size;
                if (x >= screenBounds.width) {
                    x -= screenBounds.width;
                }
                break;
            case DOWN:
                y += size;
                if (y >= screenBounds.height) {
                    y -= screenBounds.height;
                }
                break;
            case LEFT:
                x -= size;
                if (x <= 0) {
                    x += screenBounds.width;
                }
                break;
        }
    }
}
