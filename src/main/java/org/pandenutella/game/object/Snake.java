package org.pandenutella.game.object;

import lombok.Data;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@Data
public class Snake implements GameObject {

    private final int size;
    private final SnakeHead head;
    private final List<SnakeBody> bodyList;

    private int x;
    private int y;

    public Snake(int size, int x, int y, int length) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.head = this.initializeHead();
        this.bodyList = this.initializeBody(length);
    }

    private SnakeHead initializeHead() {
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
            int x = front == null ? this.x : front.getX();

            int y = front == null ? this.y : front.getY();
            y += size;

            SnakeBody body = new SnakeBody(size, x, y);

            if (front != null) {
                body.setFront(front);
                front.setBack(body);
            }

            bodyList.add(body);
            front = body;
        }

        return bodyList;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        bodyList.forEach(body -> body.render(g));
        head.render(g);
    }
}
