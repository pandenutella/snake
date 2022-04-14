package implementation.snake;

import implementation.constants.Direction;
import lombok.Data;

@Data
public class SnakeHead {
    private Direction direction;
    private int x;
    private int y;

    public void moveTowardsDirection(int blocks) {
        switch (direction) {
            case UP:
                y -= blocks;
                break;
            case RIGHT:
                x += blocks;
                break;
            case DOWN:
                y += blocks;
                break;
            case LEFT:
                x -= blocks;
                break;
            default:
                break;
        }

        if (y < 0)
            y = blocks * 14;
        else if (y > blocks * 14)
            y = 0;

        if (x < 0)
            x = blocks * 14;
        else if (x > blocks * 14)
            x = 0;
    }
}
