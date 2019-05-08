package com.kata.tetris.ui;

import com.kata.tetris.domain.Tetris;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.kata.tetris.domain.Command.*;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private final Tetris tetris;

    public KeyEventHandler(Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void handle(KeyEvent key) {
        if (tetris.isOnGoingGame()) {
            if (key.getCode() == KeyCode.UP) {
                ROTATE.applyOn(tetris);
            }
            if (key.getCode() == KeyCode.RIGHT) {
                RIGHT.applyOn(tetris);
            }
            if (key.getCode() == KeyCode.LEFT) {
                LEFT.applyOn(tetris);
            }
            if (key.getCode() == KeyCode.DOWN) {
                DOWN.applyOn(tetris);
            }
        }
        
    }
}
