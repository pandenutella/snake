package implementation;

import framework.Game;

import static implementation.constants.ViewConstants.START;

public class SnakeGame {

    public SnakeGame() {
        Game game = new Game();
        game.initialize();

        game.getViewManager().addView(START);
        game.getViewManager().goToView(START);

        game.run();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
