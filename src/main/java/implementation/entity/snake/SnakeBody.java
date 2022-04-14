package implementation.entity.snake;

import lombok.Data;

@Data
public class SnakeBody {
    private int col;
    private int row;

    public void moveTo(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
