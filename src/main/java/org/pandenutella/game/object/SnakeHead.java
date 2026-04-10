package org.pandenutella.game.object;

import lombok.Getter;
import lombok.Setter;
import org.pandenutella.game.constant.Direction;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeHead implements GameObject {

    private final int size;

    @Getter
    @Setter
    private int x;

    @Getter
    @Setter
    private int y;

    public SnakeHead(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
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
                break;
            case RIGHT:
                x += size;
                break;
            case DOWN:
                y += size;
                break;
            case LEFT:
                x -= size;
                break;
        }
    }
}
