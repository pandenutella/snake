package org.pandenutella.game.object.collision;

import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.global.object.ObjectManager;
import org.pandenutella.game.object.Updatable;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager implements Updatable {

    private static CollisionManager INSTANCE;

    public static void initialize() {
        INSTANCE = new CollisionManager();
    }

    public static CollisionManager getInstance() {
        return INSTANCE;
    }

    private final List<Collider> colliderList = new ArrayList<>();
    private final List<Collidee> collideeList = new ArrayList<>();

    public CollisionManager() {
        ObjectManager.getInstance().addUpdatable(UpdatePriority.POST_PROCESS, this);
    }

    public void addCollider(Collider collider) {
        colliderList.add(collider);
    }

    public void addCollidee(Collidee collidee) {
        collideeList.add(collidee);
    }

    @Override
    public void update() {
        for (Collider collider : colliderList) {
            if (collider == null) {
                continue;
            }

            for (Collidee collidee : collideeList) {
                if (!collider.getCollideeList().contains(collidee.getClass())
                        || !collider.getPosition().equals(collidee.getPosition())
                ) {
                    continue;
                }

                collider.collide(collidee);
            }
        }
    }
}
