package org.pandenutella.game.object.snake;

import lombok.Data;
import org.pandenutella.game.constant.Direction;
import org.pandenutella.game.global.object.GridManager;
import org.pandenutella.game.global.object.ObjectManager;
import org.pandenutella.game.object.DirectionControllable;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.grid.CellPositioned;
import org.pandenutella.game.utility.Position;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@Data
public class Snake implements GameObject, DirectionControllable, CellPositioned {

    private final int size;
    private final SnakeHead head;
    private final List<SnakeBody> bodyList;
    private final Dimension screenBounds;

    private Direction direction = Direction.UP;

    public Snake(int size, Position position, int length, Dimension screenBounds) {
        this.size = size;
        this.head = this.initializeHead(position, screenBounds);
        this.bodyList = this.initializeBody(length);
        this.screenBounds = screenBounds;

        ObjectManager.getInstance().addGameObject(this);
        GridManager.getInstance().addCellPositioned(this);
    }

    private SnakeHead initializeHead(Position position, Dimension screenBounds) {
        return new SnakeHead(size, position, screenBounds);
    }

    private List<SnakeBody> initializeBody(int length) {
        List<SnakeBody> bodyList = new ArrayList<>();
        if (length <= 1) {
            return bodyList;
        }

        int bodyCount = length - 1;
        SnakeBody front = null;
        for (int i = 1; i <= bodyCount; i++) {
            Position position = Position.copy(front == null
                    ? head.getPosition()
                    : front.getPosition());
            position.offsetY(size);

            SnakeBody body = new SnakeBody(size, position);
            body.setFront(front);

            bodyList.add(body);
            front = body;
        }

        return bodyList;
    }

    @Override
    public void update() {
        handleMovement();
    }

    private void handleMovement() {
        for (int i = bodyList.size() - 1; i >= 0; i--) {
            SnakeBody body = bodyList.get(i);
            SnakeBody front = body.getFront();

            Position nextPosition = front == null ? head.getPosition() : front.getPosition();
            body.getPosition().moveTo(nextPosition);
        }

        head.moveTowards(direction);
    }

    @Override
    public void render(Graphics g) {
        bodyList.forEach(body -> body.render(g));
        head.render(g);
    }

    @Override
    public List<Position> getPositions() {
        List<Position> positionList = new ArrayList<>();

        positionList.add(this.head.getPosition());
        positionList.addAll(this.bodyList.stream()
                .map(SnakeBody::getPosition)
                .toList());

        return positionList;
    }
}
