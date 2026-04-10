package org.pandenutella.game.framework;

import lombok.RequiredArgsConstructor;
import org.pandenutella.game.object.Updatable;

import java.util.List;

@RequiredArgsConstructor
public class GameLoop implements Runnable {

    private final double movementPerSecond;
    private final Panel panel;
    private final List<? extends Updatable> controllerList;
    private final List<? extends Updatable> gameObjectList;

    private boolean running;

    public void start() {
        if (running) {
            return;
        }

        running = true;
        System.out.println("Game loop starts");

        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastNano = System.nanoTime();

        while (running) {
            long currentNano = System.nanoTime();

            if (currentNano - lastNano >= getNanoPerMovement()) {
                update();
                render();

                lastNano = currentNano;
            }
        }
    }

    private double getNanoPerMovement() {
        return 1000000000 / movementPerSecond;
    }

    private void update() {
        controllerList.forEach(Updatable::update);
        gameObjectList.forEach(Updatable::update);
    }

    private void render() {
        panel.repaint();
    }
}
