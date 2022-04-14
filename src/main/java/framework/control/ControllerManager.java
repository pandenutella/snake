package framework.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ControllerManager implements KeyListener {

    private final List<Controllable> controllableList = new ArrayList<>();

    public void addControllable(Controllable controllable) {
        controllableList.add(controllable);
    }

    public void removeControllable(Controllable controllable) {
        controllableList.remove(controllable);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        try {
            for (Controllable controllable : controllableList) {
                if (!controllable.getListenedKeyCodes().contains(keyCode))
                    continue;

                controllable.performAction(keyCode);
            }
        } catch (ConcurrentModificationException ignored) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
