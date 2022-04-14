package framework.display;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

import static java.awt.Color.WHITE;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameScreen extends JPanel {

    private PaintManager paintManager;

    public GameScreen(int size) {
        setPreferredSize(new Dimension(size, size));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (PaintLevel paintLevel : PaintLevel.values())
            paintManager.getPainterMap().get(paintLevel).forEach(painter -> painter.paint(g));
    }
}
