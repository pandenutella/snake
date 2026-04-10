package org.pandenutella.game.constant;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    public boolean isAdjacent(Direction direction) {
        return ((this == Direction.UP || this == Direction.DOWN) && (direction == Direction.LEFT || direction == Direction.RIGHT)) ||
                ((this == Direction.RIGHT || this == Direction.LEFT) && (direction == Direction.UP || direction == Direction.DOWN));
    }
}
