import window.GameScreen;
import window.GameWindow;
import window.PaintManager;

public class SnakeGame {

    public SnakeGame() {
        PaintManager paintManager = new PaintManager();

        GameScreen gameScreen = new GameScreen();
        gameScreen.setPaintManager(paintManager);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setGameScreen(gameScreen);

        gameWindow.initialize();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
