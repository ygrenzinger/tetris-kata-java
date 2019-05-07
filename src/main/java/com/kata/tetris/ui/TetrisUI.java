package com.kata.tetris.ui;

import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.UpdateUI;
import com.kata.tetris.domain.field.Field;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static com.kata.tetris.domain.field.Field.DEFAULT_HEIGHT;
import static com.kata.tetris.domain.field.FieldRow.DEFAULT_WIDTH;

public class TetrisUI extends GridPane implements UpdateUI {

    private static final int GRID_SIZE_GAP = 3;
    private static final int CELL_SIZE = 20;
    private static final String STYLE_SCORE =
            "-fx-text-fill: WHITE;" +
                    "-fx-font-size: 26 px;" +
                    "-fx-text-alignment: center";
    private static final String SCORE_LABEL_TEXT = "SCORE : ";
    private final List<List<Label>> cellGrid;
    private final Label labelScore;

    public TetrisUI() {
        super();
        this.cellGrid = initGrid();
        this.labelScore = initLabelScore();
        initGridPane(this.labelScore);
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
        System.out.println(score);
        labelScore.setText(SCORE_LABEL_TEXT + score);
    }

    private static Label initLabelScore() {
        Label labelScore = new Label(SCORE_LABEL_TEXT + 0);
        labelScore.setStyle(STYLE_SCORE);
        return labelScore;
    }

    private void initGridPane(Label labelScore) {
        setHgap(GRID_SIZE_GAP);
        setVgap(GRID_SIZE_GAP);
        setAlignment(Pos.CENTER);

        setStyle("-fx-background-color : black");
        setPrefSize(
                DEFAULT_WIDTH * (CELL_SIZE + GRID_SIZE_GAP),
                DEFAULT_HEIGHT * (CELL_SIZE + GRID_SIZE_GAP) + 30
        );
        add(labelScore, 0, DEFAULT_HEIGHT, DEFAULT_WIDTH, 1);
    }

    private List<List<Label>> initGrid() {
        List<List<Label>> cellGrid = new ArrayList<>();
        for (int row = DEFAULT_HEIGHT - 1; row >= 0; row--) {
            ArrayList<Label> cellRow = new ArrayList<>();
            cellGrid.add(cellRow);
            for (int column = 0; column < DEFAULT_WIDTH; column++) {
                Label label = new Label();

                label.setStyle("-fx-background-color : white");
                label.setPrefSize(CELL_SIZE, CELL_SIZE);

                cellRow.add(label);
                add(label, column, row);
            }
        }
        return cellGrid;
    }
}
