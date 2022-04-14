package implementation.entity.snake;

import implementation.constants.Direction;
import lombok.Data;

@Data
public class SnakeHead {
    private Direction direction;
    private int col;
    private int row;

    public void moveTowardsDirection() {
        switch (direction) {
            case UP:
                row -= 1;
                break;
            case RIGHT:
                col += 1;
                break;
            case DOWN:
                row += 1;
                break;
            case LEFT:
                col -= 1;
                break;
            default:
                break;
        }

        if (row < 0)
            row = 14;
        else if (row > 14)
            row = 0;

        if (col < 0)
            col = 14;
        else if (col > 14)
            col = 0;
    }
}
