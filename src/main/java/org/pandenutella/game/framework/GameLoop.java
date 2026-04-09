package org.pandenutella.game.framework;

import lombok.RequiredArgsConstructor;
import org.pandenutella.game.object.GameObject;

import java.util.List;

@RequiredArgsConstructor
public class GameLoop implements Runnable {

    private static final double MOVEMENT_PER_SECOND = 2.0;
    private static final double NANO_PER_MOVEMENT = 1000000000 / MOVEMENT_PER_SECOND;

    private final Panel panel;
    private final List<GameObject> gameObjectList;

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

            if (currentNano - lastNano >= NANO_PER_MOVEMENT) {
                update();
                render();

                lastNano = currentNano;
            }
        }
    }

    private void update() {
        gameObjectList.forEach(GameObject::update);
    }

    private void render() {
        panel.repaint();
    }
}
