package com.kata.tetris;

import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.Tetris;
import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.RandomTetrominoGenerator;
import com.kata.tetris.domain.tetromino.ShapeFactory;
import com.kata.tetris.infra.FileShapeLoader;
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
    private RandomTetrominoGenerator randomTetrominoGenerator;

    private ScheduledFuture<?> updateUIHandle;
    private ScheduledExecutorService scheduler;

    private Runnable update = () -> {
        tetris.updateGame();
        if (tetris.isGameOver()) {
            stop();
        }
    };

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
        updateUIHandle = scheduler.scheduleAtFixedRate(update, 1, 1, SECONDS);
    }

    @Override
    public void init() {
        FileShapeLoader shapeLoader = new FileShapeLoader();
        ShapeFactory shapeFactory = new ShapeFactory(shapeLoader);
        randomTetrominoGenerator = new RandomTetrominoGenerator(shapeFactory);
        tetrisUI = new TetrisUI();
        tetris = new Tetris(tetrisUI, () -> randomTetrominoGenerator.randomShape(), new Field(), new Score());
    }

    @Override
    public void stop() {
        updateUIHandle.cancel(true);
        scheduler.shutdown();
    }
}
