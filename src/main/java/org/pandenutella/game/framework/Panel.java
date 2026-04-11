package org.pandenutella.game.framework;

import lombok.Getter;
import org.pandenutella.game.global.object.ObjectManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private static Panel INSTANCE;

    public static void initialize(Dimension dimension) {
        INSTANCE = new Panel(dimension);
    }

    public static Panel getInstance() {
        return INSTANCE;
    }

    @Getter
    private final Dimension dimension;

    public Panel(Dimension dimension) {
        this.dimension = dimension;

        this.setPreferredSize(dimension);
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ObjectManager.getInstance().getRenderableList().forEach(renderable -> {
            try {
                renderable.render(g);
            } catch (Exception ignored) {
            }
        });
    }
}
