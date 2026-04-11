package org.pandenutella.game.object;

import lombok.Getter;
import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.global.object.ObjectManager;

public class StopWatch implements Updatable {

    private final int duration;

    private int current;

    @Getter
    private boolean running = false;

    public StopWatch(int duration) {
        this.duration = duration;
        reset();

        ObjectManager.getInstance().addUpdatable(UpdatePriority.PRE_PROCESS, this);
    }

    private void tick() {
        current = Math.min(current + 1, duration - 1);
    }

    public void start() {
        running = true;
    }

    public boolean isElapsed() {
        return current >= duration - 1;
    }

    @Override
    public void update() {
        if (!running) {
            return;
        }

        if (isElapsed()) {
            reset();
            return;
        }

        tick();
    }

    private void reset() {
        current = 1;
        running = false;
    }
}
