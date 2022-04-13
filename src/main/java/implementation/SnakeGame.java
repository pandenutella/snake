package implementation;

import framework.Game;

import static implementation.constants.ViewConstants.PLAY;
import static implementation.constants.ViewConstants.START;
import static java.util.Arrays.asList;

public class SnakeGame {

    public SnakeGame() {
        Game game = new Game();
        game.setName("Snake - pandenutella");
        game.initialize();

        game.getViewManager().setViews(asList(START, PLAY));
        game.getViewManager().goToView(START);

        game.run();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
