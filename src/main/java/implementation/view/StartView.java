package implementation.view;

import framework.control.Controllable;
import framework.display.PaintUtility;
import framework.view.View;
import lombok.Getter;

import java.awt.Graphics;
import java.util.List;

import static implementation.constants.ViewConstants.PLAY;
import static java.awt.Color.WHITE;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.util.Collections.singletonList;

@Getter
public class StartView extends View implements Controllable {

    private PaintUtility paintUtility;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
        getControllerManager().addControllable(this);
    }

    @Override
    public void unmount() {
        paintUtility = null;
        getControllerManager().removeControllable(this);
    }

    @Override
    public void paint(Graphics g) {
        paintUtility.drawTitle(g, "SNAKE", 100, 150, WHITE);
        paintUtility.drawText(g, "Press [SPACE] to start", 100, 175, WHITE);
    }

    @Override
    public List<Integer> getListenedKeyCodes() {
        return singletonList(VK_SPACE);
    }

    @Override
    public void performAction(int keyCode) {
        if (keyCode != VK_SPACE)
            return;

        getControllerManager().removeControllable(this);
        getViewManager().goToView(PLAY);
    }
}
