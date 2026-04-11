package org.pandenutella.game.object.apple;

import org.pandenutella.game.global.object.GridManager;
import org.pandenutella.game.global.object.ObjectManager;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.grid.CellPositioned;
import org.pandenutella.game.utility.Position;
import org.pandenutella.game.utility.StopWatch;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AppleSpawner implements GameObject, CellPositioned {

    private final int size;
    private final StopWatch cooldownTimer;

    private Apple apple;

    public AppleSpawner(int size, int cooldown) {
        this.size = size;
        this.cooldownTimer = new StopWatch(cooldown);

        ObjectManager.getInstance().addGameObject(this);
        GridManager.getInstance().addCellPositioned(this);
    }

    @Override
    public void render(Graphics g) {
        if (apple == null) {
            return;
        }

        apple.render(g);
    }

    @Override
    public void update() {
        if (apple != null) {
            return;
        }

        if (cooldownTimer.isElapsed()) {
            cooldownTimer.reset();
            spawnApple();
        } else {
            cooldownTimer.tick();
        }
    }

    private void spawnApple() {
        List<Position> vacantPositionList = GridManager.getInstance().getVacantPositionList();
        int randomIndex = vacantPositionList.size();
        Position randomVacantPosition = vacantPositionList.get(ThreadLocalRandom.current().nextInt(randomIndex));

        apple = new Apple(size, Position.copy(randomVacantPosition));
    }

    @Override
    public List<Position> getPositions() {
        if (apple == null) {
            return Collections.emptyList();
        }

        return List.of(apple.getPosition());
    }
}
