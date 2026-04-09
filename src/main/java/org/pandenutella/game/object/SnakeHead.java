package org.pandenutella.game.object;

import lombok.Setter;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeHead implements GameObject {

    private final int size;

    private int x;
    private int y;

    @Setter
    private GameObject back;

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
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, size, size);
    }
}
