package window;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import static java.awt.Color.BLACK;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameScreen extends JPanel {

    private PaintManager paintManager;

    public GameScreen() {
        setPreferredSize(new Dimension(700, 700));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        paintManager.getPainterList().forEach(painter -> painter.paint(g));
    }
}
