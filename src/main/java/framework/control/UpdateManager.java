package framework.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateManager {

    private final Map<UpdateLevel, List<Updatable>> updatableMap = new HashMap<>();

    public UpdateManager() {
        for (UpdateLevel updateLevel : UpdateLevel.values())
            updatableMap.put(updateLevel, new ArrayList<>());
    }

    public void addUpdatable(UpdateLevel updateLevel, Updatable updatable) {
        updatableMap.get(updateLevel).add(updatable);
    }

    public void removeUpdatable(UpdateLevel updateLevel, Updatable updatable) {
        updatableMap.get(updateLevel).remove(updatable);
    }

    public void update() {
        for (UpdateLevel updateLevel : UpdateLevel.values())
            updatableMap.get(updateLevel).forEach(Updatable::update);
    }
}
