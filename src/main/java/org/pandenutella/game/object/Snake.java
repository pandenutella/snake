package org.pandenutella.game.object;

import lombok.Data;
import org.pandenutella.game.constant.Direction;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@Data
public class Snake implements GameObject, DirectionControllable {

    private final int size;
    private final SnakeHead head;
    private final List<SnakeBody> bodyList;

    private Direction direction = Direction.UP;

    public Snake(int size, int x, int y, int length) {
        this.size = size;
        this.head = this.initializeHead(x, y);
        this.bodyList = this.initializeBody(length);
    }

    private SnakeHead initializeHead(int x, int y) {
        return new SnakeHead(size, x, y);
    }

    private List<SnakeBody> initializeBody(int length) {
        List<SnakeBody> bodyList = new ArrayList<>();
        if (length <= 1) {
            return bodyList;
        }

        int bodyCount = length - 1;
        SnakeBody front = null;
        for (int i = 1; i <= bodyCount; i++) {
            int x = front == null ? head.getX() : front.getX();

            int y = front == null ? head.getY() : front.getY();
            y += size;

            SnakeBody body = new SnakeBody(size, x, y);
            body.setFront(front);

            bodyList.add(body);
            front = body;
        }

        return bodyList;
    }

    @Override
    public void update() {
        for (int i = bodyList.size() - 1; i >= 0; i--) {
            SnakeBody body = bodyList.get(i);
            SnakeBody front = body.getFront();

            int x = front == null ? head.getX() : front.getX();
            int y = front == null ? head.getY() : front.getY();
            body.moveTo(x, y);
        }

        head.moveTowards(direction);
    }

    @Override
    public void render(Graphics g) {
        bodyList.forEach(body -> body.render(g));
        head.render(g);
    }
}
