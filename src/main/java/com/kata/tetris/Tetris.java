package com.kata.tetris;

import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.field.Field;
import com.kata.tetris.infra.RandomTetromino;
import com.kata.tetris.ui.TetrisUI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Tetris {

    private final Field field;
    private final RandomTetromino randomTetromino;
    private final TetrisUI tetrisUI;
    private final Score score;

    private ScheduledFuture<?> updateUIHandle;
    private ScheduledExecutorService scheduler;

    private boolean running;

    public Tetris(TetrisUI tetrisUI) {
        this.tetrisUI = tetrisUI;
        this.field = new Field();
        this.randomTetromino = new RandomTetromino();
        this.score = new Score();
    }

    public void startGame() {
        field.createNewTetrominoAtTop(randomTetromino.randomShape());
        scheduler = Executors.newScheduledThreadPool(1);
        updateUIHandle = scheduler.scheduleAtFixedRate(this::updateGame, 1, 1, SECONDS);
        running = true;
    }

    public void stopGame() {
        running = false;
        updateUIHandle.cancel(true);
        scheduler.shutdown();
    }

    private void updateGame() {
        field.moveDownTetrominoAndFixIfReachedTheGround(randomTetromino::randomShape);
        updateUI();
    }

    public void updateUI() {
        this.tetrisUI.updateUI(this.field, score);
    }

    public boolean onGoingGame() {
        return running;
    }

    public void rotateTetromino() {
        field.rotateTetromino();
    }

    public void moveRightTetromino() {
        field.moveRightTetromino();
    }

    public void moveLeftTetromino() {
        field.moveLeftTetromino();
    }

    public void moveDownTetromino() {
        field.moveDownTetromino();
    }
}
