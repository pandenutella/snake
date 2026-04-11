package org.pandenutella.game.object.apple;

import lombok.Getter;
import org.pandenutella.game.global.object.GridManager;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.StopWatch;
import org.pandenutella.game.object.eat.Eater;
import org.pandenutella.game.object.eat.EatingManager;
import org.pandenutella.game.object.eat.Food;
import org.pandenutella.game.object.grid.CellPositioned;
import org.pandenutella.game.utility.Position;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Apple extends GameObject implements CellPositioned, Food {

    private final StopWatch respawnTimer = new StopWatch(3);

    private final int size;

    @Getter
    private Position position;

    public Apple(int size) {
        this.size = size;

        GridManager.getInstance().addCellPositioned(this);
        EatingManager.getInstance().addFood(this);

        respawnTimer.start();
    }

    @Override
    public void update() {
        if (respawnTimer.isElapsed()) {
            spawn();
        }
    }

    public void render(Graphics g) {
        if (respawnTimer.isRunning()) {
            return;
        }

        g.setColor(Color.RED);
        g.fillRect(position.getX(), position.getY(), size, size);
    }

    @Override
    public void eaten(Eater eater) {
        respawnTimer.start();
    }

    @Override
    public List<Position> getPositions() {
        if (respawnTimer.isRunning()) {
            return Collections.emptyList();
        }

        return List.of(position);
    }

    private void spawn() {
        List<Position> vacantPositionList = GridManager.getInstance().getVacantPositionList();
        int randomIndex = vacantPositionList.size();
        Position randomVacantPosition = vacantPositionList.get(ThreadLocalRandom.current().nextInt(randomIndex));

        if (position == null) {
            position = Position.copy(randomVacantPosition);
        } else {
            position.moveTo(randomVacantPosition);
        }

    }
}
