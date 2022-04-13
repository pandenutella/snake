package implementation.view;

import framework.display.PaintUtility;
import framework.view.View;

import java.awt.Graphics;

import static java.awt.Color.WHITE;

public class StartView extends View {

    private PaintUtility paintUtility;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
    }

    @Override
    public void unmount() {
        paintUtility = null;
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        paintUtility.drawTitle(g, "SNAKE", 100, 150, WHITE);
        paintUtility.drawText(g, "Press [SPACE] to start", 100, 175, WHITE);
    }
}
