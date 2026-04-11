package org.pandenutella.game.global.object;

import lombok.Getter;
import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.Renderable;
import org.pandenutella.game.object.Updatable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectManager {

    private static ObjectManager INSTANCE;

    public static ObjectManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectManager();
        }

        return INSTANCE;
    }

    @Getter
    private final Map<UpdatePriority, List<Updatable>> priorityUpdatableListMap;

    @Getter
    private final List<Renderable> renderableList = new ArrayList<>();

    private ObjectManager() {
        priorityUpdatableListMap = new HashMap<>();
        priorityUpdatableListMap.put(UpdatePriority.INPUT, new ArrayList<>());
        priorityUpdatableListMap.put(UpdatePriority.PRE_PROCESS, new ArrayList<>());
        priorityUpdatableListMap.put(UpdatePriority.GAME_OBJECT, new ArrayList<>());
        priorityUpdatableListMap.put(UpdatePriority.POST_PROCESS, new ArrayList<>());
    }

    public void addUpdatable(UpdatePriority priority, Updatable updatable) {
        priorityUpdatableListMap.get(priority).add(updatable);
    }

    public void addRenderable(Renderable renderable) {
        renderableList.add(renderable);
    }

    public void addGameController(GameController gameController) {
        priorityUpdatableListMap.get(UpdatePriority.INPUT).add(gameController);
        Window.getInstance().addKeyListener(gameController);
    }
}
