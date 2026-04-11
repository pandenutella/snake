package org.pandenutella.game.object.apple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.utility.Position;

import java.awt.Color;
import java.awt.Graphics;

@RequiredArgsConstructor
public class Apple implements GameObject {

    private final int size;

    @Getter
    private final Position position;

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX(), position.getY(), size, size);
    }

    @Override
    public void update() {

    }
}
