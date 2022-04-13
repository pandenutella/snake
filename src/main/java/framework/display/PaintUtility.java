package framework.display;

import lombok.NoArgsConstructor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static java.awt.Font.BOLD;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PaintUtility {

    private static PaintUtility INSTANCE;

    public static PaintUtility getInstance() {
        if (isNull(INSTANCE))
            INSTANCE = new PaintUtility();

        return INSTANCE;
    }

    public void drawTitle(Graphics g, String title, int level, int x, int y, Color color) {
        Color initialColor = g.getColor();
        Font initialFont = g.getFont();

        int fontSize = 72 - (8 * (level - 1));
        Font font = new Font(initialFont.getFontName(), BOLD, fontSize);

        g.setColor(color);
        g.setFont(font);

        g.drawString(title, x, y);

        g.setColor(initialColor);
        g.setFont(initialFont);
    }

    public void drawTitle(Graphics g, String title, int x, int y, Color color) {
        Color initialColor = g.getColor();
        Font initialFont = g.getFont();

        Font font = new Font(initialFont.getFontName(), BOLD, 72);

        g.setColor(color);
        g.setFont(font);

        g.drawString(title, x, y);

        g.setColor(initialColor);
        g.setFont(initialFont);
    }

    public void drawText(Graphics g, String text, int x, int y, Color color) {
        Color initialColor = g.getColor();

        g.setColor(color);
        g.drawString(text, x, y);
        g.setColor(initialColor);
    }
}
