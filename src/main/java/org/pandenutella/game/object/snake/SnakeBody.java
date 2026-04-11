package org.pandenutella.game.object.snake;

import lombok.Getter;
import lombok.Setter;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.utility.Position;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody implements GameObject {

    private final int size;

    @Getter
    private final Position position;

    @Getter
    @Setter
    private SnakeBody front;

    public SnakeBody(int size, Position position) {
        this.size = size;
        this.position = position;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getPosition().getX(), getPosition().getY(), size, size);
    }

}
