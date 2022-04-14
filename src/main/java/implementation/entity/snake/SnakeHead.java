package implementation.entity.snake;

import implementation.constants.Direction;
import lombok.Data;

import static implementation.constants.GameConstants.BLOCKS;

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
            row = BLOCKS - 1;
        else if (row > BLOCKS - 1)
            row = 0;

        if (col < 0)
            col = BLOCKS - 1;
        else if (col > BLOCKS - 1)
            col = 0;
    }
}
