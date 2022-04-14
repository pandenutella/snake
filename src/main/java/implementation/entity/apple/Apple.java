package implementation.entity.apple;

import framework.control.Updatable;
import framework.display.PaintUtility;
import framework.display.Painter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import static implementation.constants.GameConstants.BLOCK_SIZE;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;

public class Apple implements Updatable, Painter {

    private int col;
    private int row;

    private Color color = LIGHT_GRAY;

    public Apple() {
        spawn();
    }

    public void spawn() {
        while (true) {
            Random random = new Random();
            int newCol = random.nextInt(15);
            int newRow = random.nextInt(15);

            if (col == newCol && row == newRow)
                continue;

            col = newCol;
            row = newRow;
            break;
        }
    }

    @Override
    public void update() {
        color = color == LIGHT_GRAY ? DARK_GRAY : LIGHT_GRAY;
    }

    @Override
    public void paint(Graphics g) {
        PaintUtility.getInstance().fillRect(g, col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, color);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
