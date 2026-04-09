package org.pandenutella.game.object;

import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody implements GameObject {

    private final int size;

    @Getter
    private int x;
    @Getter
    private int y;

    @Getter
    @Setter
    private SnakeBody front;

    public SnakeBody(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, size, size);
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
