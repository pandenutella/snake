package org.pandenutella.game.framework;

import org.pandenutella.game.object.GameObject;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public class Panel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private final List<GameObject> gameObjectList;

    public Panel(Dimension dimension, List<GameObject> gameObjectList) {
        this.setPreferredSize(dimension);
        this.gameObjectList = gameObjectList;

        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        gameObjectList.forEach(gameObject -> gameObject.render(g));
    }
}
