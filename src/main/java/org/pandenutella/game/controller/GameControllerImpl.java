package org.pandenutella.game.controller;

import org.pandenutella.game.global.object.ObjectManager;

import java.awt.event.KeyEvent;

public abstract class GameControllerImpl implements GameController {
    public GameControllerImpl() {
        ObjectManager.getInstance().addGameController(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
