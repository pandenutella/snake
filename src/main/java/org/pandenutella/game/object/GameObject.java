package org.pandenutella.game.object;

import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.global.object.ObjectManager;

public abstract class GameObject implements Updatable, Renderable {
    public GameObject() {
        ObjectManager.getInstance().addUpdatable(UpdatePriority.GAME_OBJECT, this);
        ObjectManager.getInstance().addRenderable(this);
    }
}
