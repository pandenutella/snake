package framework.control;

import java.util.ArrayList;
import java.util.List;

public class UpdateManager {

    private final List<Updatable> updatableList = new ArrayList<>();

    public void addUpdatable(Updatable updatable) {
        updatableList.add(updatable);
    }

    public void removeUpdatable(Updatable updatable) {
        updatableList.remove(updatable);
    }

    public void update() {
        updatableList.forEach(Updatable::update);
    }
}
