package framework;

import framework.display.GameScreen;
import framework.display.GameWindow;
import framework.display.PaintManager;
import framework.view.ViewManager;

import static java.lang.Thread.sleep;

public class Game {

    private GameScreen gameScreen;
    private ViewManager viewManager;

    public void initialize() {
        PaintManager paintManager = new PaintManager();

        gameScreen = new GameScreen();
        gameScreen.setPaintManager(paintManager);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setGameName("Snake - pandenutella");
        gameWindow.setGameScreen(gameScreen);

        viewManager = new ViewManager();
        viewManager.setPaintManager(paintManager);

        gameWindow.initialize();
    }

    public void run() {
        while (true) {
            viewManager.update();
            gameScreen.repaint();

            try {
                sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
