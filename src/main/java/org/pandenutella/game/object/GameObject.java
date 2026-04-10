package org.pandenutella.game.object;

import java.awt.Graphics;

public interface GameObject extends Updatable {
    void render(Graphics g);
}
