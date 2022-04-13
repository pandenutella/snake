package framework.control;

import java.util.List;

public interface Controllable {

    List<Integer> getListenedKeyCodes();

    void performAction(int keyCode);
}
