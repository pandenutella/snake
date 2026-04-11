package org.pandenutella.game.object.eat;

import org.pandenutella.game.utility.Position;

import java.util.List;

public interface Eater {
    List<Class<? extends Food>> getFoodList();

    void eat(Food food);

    Position getPosition();
}
