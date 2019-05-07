package com.kata.tetris.ui;

import com.kata.tetris.Tetris;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private final Tetris tetris;

    public KeyEventHandler(Tetris tetris) {
        this.tetris = tetris;
    }

    @Override
    public void handle(KeyEvent key) {
        if (tetris.onGoingGame()) {
            if (key.getCode() == KeyCode.UP) {
                tetris.rotateTetromino();
            }
            if (key.getCode() == KeyCode.RIGHT) {
                tetris.moveRightTetromino();
            }
            if (key.getCode() == KeyCode.LEFT) {
                tetris.moveLeftTetromino();
            }
            if (key.getCode() == KeyCode.DOWN) {
                tetris.moveDownTetromino();
            }
            tetris.updateUI();
        }
        
    }
}
