package com.kata.tetris;

import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.field.Field;
import com.kata.tetris.ui.KeyEventHandler;
import com.kata.tetris.ui.TetrisUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static com.kata.tetris.domain.field.Field.DEFAULT_HEIGHT;
import static com.kata.tetris.domain.field.FieldRow.DEFAULT_WIDTH;

public class TetrisFX extends Application implements TetrisUI {

    private static final int GRID_SIZE_GAP = 3;
    private static final int CELL_SIZE = 20;
    private static final String STYLE_SCORE =
            "-fx-text-fill: WHITE;" +
            "-fx-font-size: 26 px;" +
            "-fx-text-alignment: center";
    private static final String SCORE_LABEL_TEXT = "SCORE : ";

    private Tetris tetris;

    private AtomicReference<Field> fieldAtomicReference;

    private GridPane root;
    private ArrayList<ArrayList<Label>> cellGrid;
    private Label labelScore;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyEventHandler(tetris));
        root.setHgap(GRID_SIZE_GAP);
        root.setVgap(GRID_SIZE_GAP);
        root.setAlignment(Pos.CENTER);

        root.setStyle("-fx-background-color : black");
        root.setPrefSize(
                DEFAULT_WIDTH * (CELL_SIZE + GRID_SIZE_GAP),
                DEFAULT_HEIGHT * (CELL_SIZE + GRID_SIZE_GAP) + 30
        );

        labelScore.setStyle(STYLE_SCORE);
        root.add(labelScore, 0, DEFAULT_HEIGHT, DEFAULT_WIDTH, 1);
        stage.setScene(scene);
        stage.setTitle("Tetris");
        stage.show();
        tetris.startGame();
    }

    @Override
    public void init() {
        tetris = new Tetris(this);
        cellGrid = new ArrayList<>();
        root = new GridPane();
        labelScore = new Label(SCORE_LABEL_TEXT + 0);
        initGrid();
    }

    @Override
    public void updateUI(Field field, Score score) {
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            for (int column = 0; column < DEFAULT_WIDTH; column++) {
                switch (field.blockAt(row, column)) {
                    case FIXED:
                        cellGrid.get(row).get(column).setStyle("-fx-background-color : grey");
                        break;
                    case EMPTY:
                        cellGrid.get(row).get(column).setStyle("-fx-background-color : white");
                        break;
                    default:
                        cellGrid.get(row).get(column).setStyle("-fx-background-color : blue");
                        break;
                }
            }
        }
        labelScore.setText(SCORE_LABEL_TEXT + score);
    }

    @Override
    public void stop() {
        tetris.stopGame();
    }

    private void initGrid() {
        for (int row = DEFAULT_HEIGHT-1; row >= 0; row--) {
            ArrayList<Label> cellRow = new ArrayList<>();
            cellGrid.add(cellRow);
            for (int column = 0; column < DEFAULT_WIDTH; column++) {
                Label label = new Label();

                label.setStyle("-fx-background-color : white");
                label.setPrefSize(CELL_SIZE, CELL_SIZE);

                cellRow.add(label);
                root.add(label, column, row);
            }
        }
    }
}
