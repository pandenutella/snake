package framework;

import framework.control.ControllerManager;
import framework.control.UpdateManager;
import framework.display.GameScreen;
import framework.display.GameWindow;
import framework.display.PaintManager;
import framework.view.ViewManager;

import static java.lang.Thread.sleep;

public class Game {

    private String name;
    private int fps = 60;
    private GameScreen gameScreen;
    private ViewManager viewManager;
    private UpdateManager updateManager;

    public void initialize() {
        PaintManager paintManager = new PaintManager();
        ControllerManager controllerManager = new ControllerManager();
        updateManager = new UpdateManager();

        gameScreen = new GameScreen();
        gameScreen.setPaintManager(paintManager);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setGameName(name);
        gameWindow.setGameScreen(gameScreen);
        gameWindow.setControllerManager(controllerManager);

        viewManager = new ViewManager();
        viewManager.setPaintManager(paintManager);
        viewManager.setControllerManager(controllerManager);
        viewManager.setUpdateManager(updateManager);

        gameWindow.initialize();
    }

    public void run() {
        while (true) {
            updateManager.update();
            gameScreen.repaint();

            try {
                sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
