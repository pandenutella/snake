package framework;

import framework.control.ControllerManager;
import framework.display.GameScreen;
import framework.display.GameWindow;
import framework.display.PaintManager;
import framework.view.ViewManager;

import static java.lang.Thread.sleep;

public class Game {

    private String name;
    private GameScreen gameScreen;
    private ViewManager viewManager;

    public void initialize() {
        PaintManager paintManager = new PaintManager();

        gameScreen = new GameScreen();
        gameScreen.setPaintManager(paintManager);

        ControllerManager controllerManager = new ControllerManager();

        GameWindow gameWindow = new GameWindow();
        gameWindow.setGameName(name);
        gameWindow.setGameScreen(gameScreen);
        gameWindow.setControllerManager(controllerManager);

        viewManager = new ViewManager();
        viewManager.setPaintManager(paintManager);
        viewManager.setControllerManager(controllerManager);

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

    public void setName(String name) {
        this.name = name;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
