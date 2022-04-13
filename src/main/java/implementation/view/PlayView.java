package implementation.view;

import framework.control.Controllable;
import framework.display.PaintUtility;
import framework.view.View;
import lombok.Getter;

import java.awt.Graphics;
import java.util.List;

import static java.awt.Color.WHITE;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.util.Collections.singletonList;

@Getter
public class PlayView extends View implements Controllable {

    private PaintUtility paintUtility;
    private boolean paused;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
        getControllerManager().addControllable(this);

        paused = false;
    }

    @Override
    public void unmount() {
        paintUtility = null;
        getControllerManager().removeControllable(this);

        paused = false;
    }

    @Override
    public void update() {
    }

    @Override
    public void paint(Graphics g) {
        if (paused) {
            paintUtility.drawTitle(g, "Paused", 4, 100, 150, WHITE);
            paintUtility.drawText(g, "Press [ESCAPE] to resume", 100, 175, WHITE);
        }
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
