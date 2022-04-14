package implementation.view;

import framework.control.Controllable;
import framework.control.Updatable;
import framework.display.PaintUtility;
import framework.view.View;
import implementation.snake.Snake;
import lombok.Getter;

import java.awt.Graphics;
import java.util.List;

import static framework.display.PaintLevel.ENTITY;
import static java.awt.Color.WHITE;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.util.Collections.singletonList;

@Getter
public class PlayView extends View implements Updatable, Controllable {

    private PaintUtility paintUtility;
    private boolean paused;

    private Snake snake;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
        getControllerManager().addControllable(this);
        getUpdateManager().addUpdatable(this);

        paused = false;
        snake = new Snake(7, 7, 3);

        getPaintManager().addPainter(ENTITY, snake);
    }

    @Override
    public void unmount() {
        paintUtility = null;
        getControllerManager().removeControllable(this);
        getUpdateManager().removeUpdatable(this);

        paused = false;
        snake = null;

        getPaintManager().removePainter(ENTITY, snake);
    }

    @Override
    public void paint(Graphics g) {
        if (!paused)
            return;

        paintUtility.drawTitle(g, "Paused", 4, 100, 150, WHITE);
        paintUtility.drawText(g, "Press [ESCAPE] to resume", 100, 175, WHITE);
    }

    @Override
    public void update() {
        if (paused)
            return;

        System.out.println("Updating...");
    }

    @Override
    public List<Integer> getListenedKeyCodes() {
        return singletonList(VK_ESCAPE);
    }

    @Override
    public void performAction(int keyCode) {
        switch (keyCode) {
            case VK_ESCAPE:
                paused = !paused;
                break;
            default:
                break;
        }
    }
}
