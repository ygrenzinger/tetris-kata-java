package com.kata.tetris;

import com.kata.tetris.domain.Tetris;
import com.kata.tetris.infra.RandomTetromino;
import com.kata.tetris.ui.KeyEventHandler;
import com.kata.tetris.ui.TetrisUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TetrisFX extends Application {

    private Tetris tetris;
    private TetrisUI tetrisUI;
    private RandomTetromino randomTetromino;

    private ScheduledFuture<?> updateUIHandle;
    private ScheduledExecutorService scheduler;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(tetrisUI);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyEventHandler(tetris));
        stage.setScene(scene);
        stage.setTitle("Tetris");
        stage.show();
        tetris.startGame();
        scheduler = Executors.newScheduledThreadPool(1);
        updateUIHandle = scheduler.scheduleAtFixedRate(tetris::updateGame, 1, 1, SECONDS);
    }

    @Override
    public void init() {
        randomTetromino = new RandomTetromino();
        tetrisUI = new TetrisUI();
        tetris = new Tetris(tetrisUI, () -> randomTetromino.randomShape());
    }

    @Override
    public void stop() {
        tetris.stopGame();
        updateUIHandle.cancel(true);
        scheduler.shutdown();
    }
}
