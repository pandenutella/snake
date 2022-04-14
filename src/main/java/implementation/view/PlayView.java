package implementation.view;

import framework.control.Controllable;
import framework.control.Updatable;
import framework.control.UpdateLevel;
import framework.display.PaintLevel;
import framework.display.PaintUtility;
import framework.view.View;
import implementation.entity.apple.Apple;
import implementation.entity.snake.Snake;
import lombok.Getter;

import java.awt.Graphics;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Getter
public class PlayView extends View implements Updatable, Controllable {

    private PaintUtility paintUtility;
    private boolean paused;

    private Snake snake;
    private Apple apple;

    @Override
    public void mount() {
        paintUtility = PaintUtility.getInstance();
        getControllerManager().addControllable(this);

        paused = false;

        createSnake();
        createApple();

        getUpdateManager().addUpdatable(UpdateLevel.VIEW, this);
    }

    @Override
    public void unmount() {
        getUpdateManager().removeUpdatable(UpdateLevel.VIEW, this);
        paintUtility = null;
        getControllerManager().removeControllable(this);

        paused = false;

        destroySnake();
        destroyApple();
    }

    @Override
    public void update() {
        if (!snake.isAlive())
            return;

        if (nonNull(apple) && !snake.canEatApple(apple))
            return;

        snake.grow();
        apple.spawn();
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
            destroyApple();
            createSnake();
            createApple();
        } else if (snake.isAlive() && keyCode == VK_ESCAPE) {
            if (!paused) {
                getControllerManager().removeControllable(snake);
                getUpdateManager().removeUpdatable(UpdateLevel.ENTITY, snake);
                paused = true;
            } else {
                getUpdateManager().addUpdatable(UpdateLevel.ENTITY, snake);
                getControllerManager().addControllable(snake);
                paused = false;
            }
        }
    }

    private void createSnake() {
        snake = new Snake(7, 7, 3);
        getPaintManager().addPainter(PaintLevel.ENTITY, snake);
        getUpdateManager().addUpdatable(UpdateLevel.ENTITY, snake);
        getControllerManager().addControllable(snake);
    }

    private void destroySnake() {
        getControllerManager().removeControllable(snake);
        getUpdateManager().removeUpdatable(UpdateLevel.ENTITY, snake);
        getPaintManager().removePainter(PaintLevel.ENTITY, snake);
        snake = null;
    }

    private void createApple() {
        apple = new Apple();
        getPaintManager().addPainter(PaintLevel.ENTITY, apple);
        getUpdateManager().addUpdatable(UpdateLevel.ENTITY, apple);
    }

    private void destroyApple() {
        getUpdateManager().removeUpdatable(UpdateLevel.ENTITY, apple);
        getPaintManager().removePainter(PaintLevel.ENTITY, apple);
        apple = null;
    }
}
