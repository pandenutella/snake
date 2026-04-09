package org.pandenutella.game.framework;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel {

    public void setup(Dimension dimension) {
        this.setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
