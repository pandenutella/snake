package org.pandenutella.game.global.framework;

import lombok.RequiredArgsConstructor;
import org.pandenutella.game.constant.UpdatePriority;
import org.pandenutella.game.framework.Panel;
import org.pandenutella.game.global.object.ObjectManager;
import org.pandenutella.game.object.Updatable;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GameLoop implements Runnable {

    private static GameLoop INSTANCE;

    public static void initialize(double movementPerSecond) {
        INSTANCE = new GameLoop(movementPerSecond);
    }

    public static void start() {
        if (INSTANCE.running) {
            return;
        }

        INSTANCE.running = true;
        System.out.println("Game loop starts");

        INSTANCE.thread = new Thread(INSTANCE);
        INSTANCE.thread.start();
    }

    public static void stop() {
        INSTANCE.running = false;

        if (Thread.currentThread() != INSTANCE.thread) {
            try {
                INSTANCE.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final double movementPerSecond;

    private boolean running;
    private Thread thread;

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
        Map<UpdatePriority, List<Updatable>> priorityUpdatableListMap = ObjectManager.getInstance().getPriorityUpdatableListMap();
        priorityUpdatableListMap.get(UpdatePriority.INPUT).forEach(Updatable::update);
        priorityUpdatableListMap.get(UpdatePriority.PRE_PROCESS).forEach(Updatable::update);
        priorityUpdatableListMap.get(UpdatePriority.GAME_OBJECT).forEach(Updatable::update);
        priorityUpdatableListMap.get(UpdatePriority.POST_PROCESS).forEach(Updatable::update);
    }

    private void render() {
        Panel.getInstance().repaint();
    }
}
