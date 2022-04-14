package implementation.view;

import framework.control.Controllable;
import framework.display.PaintUtility;
import framework.view.View;
import implementation.snake.Snake;
import lombok.Getter;

import java.awt.Graphics;
import java.util.List;

import static framework.display.PaintLevel.ENTITY;
import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.util.Arrays.asList;

@Getter
public class PlayView extends View implements Controllable {

    private PaintUtility paintUtility;
    private boolean paused;

    private Snake snake;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
        getControllerManager().addControllable(this);

        paused = false;

        createSnake();
    }

    @Override
    public void unmount() {
        paintUtility = null;
        getControllerManager().removeControllable(this);

        paused = false;

        destroySnake();
    }

    @Override
    public void paint(Graphics g) {
        if (!snake.isAlive()) {
            paintUtility.drawTitle(g, "Game Over", 4, 100, 150, RED);
            paintUtility.drawText(g, "Press [SPACE] to play again", 100, 175, BLACK);
        } else if (paused) {
            paintUtility.drawTitle(g, "Paused", 4, 100, 150, RED);
            paintUtility.drawText(g, "Press [ESCAPE] to resume", 100, 175, BLACK);
        }
    }

    @Override
    public List<Integer> getListenedKeyCodes() {
        return asList(VK_ESCAPE, VK_SPACE);
    }

    @Override
    public void performAction(int keyCode) {
        if (!snake.isAlive() && keyCode == VK_SPACE) {
            destroySnake();
            createSnake();
        } else if (snake.isAlive() && keyCode == VK_ESCAPE) {
            if (!paused) {
                getControllerManager().removeControllable(snake);
                getUpdateManager().removeUpdatable(snake);
                paused = true;
            } else {
                getUpdateManager().addUpdatable(snake);
                getControllerManager().addControllable(snake);
                paused = false;
            }
        }
    }

    private void createSnake() {
        snake = new Snake(7, 7, 5);
        getPaintManager().addPainter(ENTITY, snake);
        getUpdateManager().addUpdatable(snake);
        getControllerManager().addControllable(snake);
    }

    private void destroySnake() {
        getControllerManager().removeControllable(snake);
        getUpdateManager().removeUpdatable(snake);
        getPaintManager().removePainter(ENTITY, snake);
        snake = null;
    }
}
