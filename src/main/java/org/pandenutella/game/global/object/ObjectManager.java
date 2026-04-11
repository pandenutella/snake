package org.pandenutella.game.global.object;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pandenutella.game.controller.GameController;
import org.pandenutella.game.framework.Window;
import org.pandenutella.game.object.GameObject;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectManager {

    private static ObjectManager INSTANCE;

    public static ObjectManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectManager();
        }

        return INSTANCE;
    }

    @Getter
    private final List<GameObject> gameObjectList = new ArrayList<>();

    @Getter
    private final List<GameController> gameControllerList = new ArrayList<>();

    public void addGameObject(GameObject gameObject) {
        gameObjectList.add(gameObject);
    }

    public void addGameController(GameController gameController) {
        gameControllerList.add(gameController);
        Window.getInstance().addGameController(gameController);
    }
}
