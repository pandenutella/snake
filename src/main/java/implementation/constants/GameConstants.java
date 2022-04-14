package implementation.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class GameConstants {
    public final static int BLOCK_SIZE = 80;
    public final static int SCREEN_SIZE = 720;
    public final static int BLOCKS = SCREEN_SIZE / BLOCK_SIZE;
}
