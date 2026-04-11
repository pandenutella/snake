package org.pandenutella.game.object.eat;

import org.pandenutella.game.utility.Position;

public interface Food {
    void eaten(Eater eater);

    Position getPosition();
}
