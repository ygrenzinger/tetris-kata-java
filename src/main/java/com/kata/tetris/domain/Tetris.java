package com.kata.tetris.domain;

import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.Shape;

import java.util.function.Supplier;

public class Tetris {

    private final Field field;
    private final Supplier<Shape> tetromino;
    private final UpdateUI updateUI;
    private Score score;
    private boolean started;

    public Tetris(UpdateUI updateUI, Supplier<Shape> tetromino, Field field, Score score) {
        this.tetromino = tetromino;
        this.updateUI = updateUI;
        this.field = field;
        this.score = score;
    }

    public void startGame() {
        field.createNewTetrominoAtTop(tetromino.get());
        started = true;
    }

    public void updateGame() {
        score.increaseScoreFor(field.movingDownAutomatically(tetromino));
        updateUI();
    }

    public boolean isGameOver() {
        return field.isFull();
    }

    public boolean isOnGoingGame() {
        return started && !field.isFull();
    }

    void apply(Command command) {
        command.applyOn(field);
        updateUI();
    }

    private void updateUI() {
        this.updateUI.updateUI(this.field, score, field.isFull());
    }
}
