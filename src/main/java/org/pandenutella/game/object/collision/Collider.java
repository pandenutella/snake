package org.pandenutella.game.object.collision;

import org.pandenutella.game.utility.Position;

import java.util.List;

public interface Collider {
    List<Class<? extends Collidee>> getCollideeList();

    void collide(Collidee collidee);

    Position getPosition();
}
