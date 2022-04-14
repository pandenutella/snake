package implementation.snake;

import lombok.Data;

@Data
public class SnakeBody {
    private int x;
    private int y;

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
