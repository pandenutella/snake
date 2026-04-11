package org.pandenutella.game.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    public static Position copy(Position position) {
        return new Position(position.getX(), position.getY());
    }

    public void offsetX(int x) {
        this.x += x;
    }

    public void offsetY(int y) {
        this.y += y;
    }

    public void moveTo(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }
}
