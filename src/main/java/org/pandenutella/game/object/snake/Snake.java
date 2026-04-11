package org.pandenutella.game.object.snake;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pandenutella.game.constant.Direction;
import org.pandenutella.game.global.framework.GameLoop;
import org.pandenutella.game.global.object.GridManager;
import org.pandenutella.game.object.DirectionControllable;
import org.pandenutella.game.object.GameObject;
import org.pandenutella.game.object.apple.Apple;
import org.pandenutella.game.object.collision.Collidee;
import org.pandenutella.game.object.collision.Collider;
import org.pandenutella.game.object.collision.CollisionManager;
import org.pandenutella.game.object.eat.Eater;
import org.pandenutella.game.object.eat.EatingManager;
import org.pandenutella.game.object.eat.Food;
import org.pandenutella.game.object.grid.CellPositioned;
import org.pandenutella.game.utility.Position;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Snake extends GameObject implements DirectionControllable, CellPositioned, Eater, Collider {

    private final int size;
    private final SnakeHead head;
    private final List<SnakeBody> bodyList;
    private final Dimension screenBounds;

    private Direction direction = Direction.UP;
    private boolean grow = false;

    public Snake(int size, Position position, int length, Dimension screenBounds) {
        super();

        this.size = size;
        this.head = this.initializeHead(position, screenBounds);
        this.bodyList = this.initializeBody(length);
        this.screenBounds = screenBounds;

        GridManager.getInstance().addCellPositioned(this);
        EatingManager.getInstance().addEater(this);
        CollisionManager.getInstance().addCollider(this);
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
        SnakeBody newBody = null;
        if (grow) {
            SnakeBody lastBody = bodyList.get(bodyList.size() - 1);
            newBody = new SnakeBody(size, Position.copy(lastBody.getPosition()));
            newBody.setFront(lastBody);
        }

        for (int i = bodyList.size() - 1; i >= 0; i--) {
            SnakeBody body = bodyList.get(i);
            SnakeBody front = body.getFront();

            Position nextPosition = front == null ? head.getPosition() : front.getPosition();
            body.getPosition().moveTo(nextPosition);
        }

        head.moveTowards(direction);

        if (grow) {
            bodyList.add(newBody);
            grow = false;
        }
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

    @Override
    public List<Class<? extends Food>> getFoodList() {
        return List.of(Apple.class);
    }

    @Override
    public void eat(Food food) {
        grow = true;
    }

    @Override
    public Position getPosition() {
        return head.getPosition();
    }

    @Override
    public List<Class<? extends Collidee>> getCollideeList() {
        return List.of(SnakeBody.class);
    }

    @Override
    public void collide(Collidee collidee) {
        GameLoop.stop();
    }
}
