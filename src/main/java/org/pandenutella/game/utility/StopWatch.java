package org.pandenutella.game.utility;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StopWatch {

    private final int value;

    private int current;

    public void tick() {
        current = Math.min(current + 1, value);
    }

    public void reset() {
        current = 0;
    }

    public boolean isElapsed() {
        return current >= value;
    }

}
