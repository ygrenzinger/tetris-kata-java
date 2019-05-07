package com.kata.tetris.domain;

import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.Shape;

import java.util.function.Supplier;

public class Tetris {

    private final Field field;
    private final Supplier<Shape> tetromino;
    private final UpdateUI updateUI;
    private Score score;

    private boolean running;

    public Tetris(UpdateUI updateUI, Supplier<Shape> tetromino) {
        this.updateUI = updateUI;
        this.field = new Field();
        this.tetromino = tetromino;
        this.score = new Score();
    }

    public void startGame() {
        field.createNewTetrominoAtTop(tetromino.get());
        running = true;
    }

    public void stopGame() {
        running = false;
    }

    public void updateGame() {
        score.increaseScoreFor(field.movingDownAutomatically(tetromino));
        updateUI();
    }

    public void updateUI() {
        this.updateUI.updateUI(this.field, score);
    }

    public boolean onGoingGame() {
        return running;
    }

    public void rotateTetromino() {
        field.rotateTetromino();
    }

    public void moveRightTetromino() {
        field.moveTetrominoRight();
    }

    public void moveLeftTetromino() {
        field.moveLeftTetromino();
    }

    public void moveDownTetromino() {
        field.moveTetrominoDown();
    }
}
