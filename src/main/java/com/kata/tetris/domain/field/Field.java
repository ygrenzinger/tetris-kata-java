package com.kata.tetris.domain.field;

import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.Tetromino;
import com.kata.tetris.domain.tetromino.TetrominoState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field {
    public static final int DEFAULT_HEIGHT = 24;
    private List<FieldRow> rows;
    private Tetromino currentTetromino;

    public Field() {
        this(DEFAULT_HEIGHT);
    }

    private Field(int height) {
        assert height > 0;
        rows = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            rows.add(new FieldRow());
        }
    }

    int height() {
        return rows.size();
    }

    public Block blockAt(int row, int column) {
        return rowAt(row).blockAt(column);
    }

    private FieldRow rowAt(int row) {
        return rows.get(row);
    }

    public List<Block> allBlocks() {
        return rows.stream().flatMap(r -> Stream.of(r.blocks())).collect(Collectors.toList());
    }

    public void createNewTetrominoAtTop(Shape shape) {
        currentTetromino = new Tetromino(shape, height() - 1, 3);
        placeCurrentTetromino();
    }

    public void rotateTetromino() {
        Optional<Tetromino> tetromino = actOnTetromino(Tetromino::rotate);
        if (!tetromino.isPresent()) {
            tryingWallKick();
        }
    }

    public void moveLeftTetromino() {
        actOnTetromino(Tetromino::moveLeft);
    }

    public void moveTetrominoRight() {
        actOnTetromino(Tetromino::moveRight);
    }

    public TetrominoState moveTetrominoDown() {
        Optional<Tetromino> tetromino = actOnTetromino(Tetromino::moveDown);
        if (!tetromino.isPresent()) {
            return TetrominoState.REACHED_GROUND;
        }
        return TetrominoState.MOVING;
    }

    public int movingDownAutomatically(Supplier<Shape> shapeSupplier) {
        TetrominoState state = moveTetrominoDown();
        if (state == TetrominoState.REACHED_GROUND) {
            fixTetromino();
            int numberOfLineRemoved = removeFullLines();
            createNewTetrominoAtTop(shapeSupplier.get());
            return numberOfLineRemoved;
        }
        return 0;
    }

    void becomesFixedBlock(int row, int column) {
        rows.set(row, rows.get(row).becomesFixedBlock(column));
    }

    private int removeFullLines() {
        int numberOfLinesRemoved = 0;
        List<FieldRow> newRow = new ArrayList<>();
        for (int i = 0; i < height(); i++) {
            FieldRow row = rowAt(i);
            if (row.isFull()) {
                numberOfLinesRemoved++;
            } else {
                newRow.add(row);
            }
        }
        for (int i = 0; i < numberOfLinesRemoved; i++) {
            newRow.add(new FieldRow());
        }
        this.rows = newRow;
        return numberOfLinesRemoved;
    }

    private void tryingWallKick() {
        if (isTetrominoLeaningOnLeftWall()) {
            actOnTetromino(currentTetromino::leftWallKick);
        } else if (isTetrominoLeaningOnRightWall()) {
            actOnTetromino(currentTetromino::rightWallKick);
        }
    }

    private boolean isTetrominoLeaningOnRightWall() {
        return currentTetromino.positionsOnField().stream().anyMatch(p -> p.getColumn() == FieldRow.DEFAULT_WIDTH - 1);
    }

    private boolean isTetrominoLeaningOnLeftWall() {
        return currentTetromino.positionsOnField().stream().anyMatch(p -> p.getColumn() == 0);
    }

    private Optional<Tetromino> actOnTetromino(Function<Tetromino, Tetromino> action) {
        Optional<Tetromino> nextTetromino = nextTetromino(action);
        nextTetromino.ifPresent(tetromino -> {
            removeCurrentTetromino();
            currentTetromino = tetromino;
            placeCurrentTetromino();
        });
        return nextTetromino;
    }

    private Optional<Tetromino> nextTetromino(Function<Tetromino, Tetromino> action) {
        Tetromino future = action.apply(currentTetromino);
        if (isPossiblePosition(future)) {
            return Optional.of(future);
        }
        return Optional.empty();
    }

    private boolean isPossiblePosition(Tetromino tetromino) {
        return tetromino.positionsOnField().stream().allMatch(this::isPossiblePosition);
    }

    private boolean isPossiblePosition(Position position) {
        return isInsideField(position) && isNotOverlappingFixedBlock(position);
    }

    private boolean isNotOverlappingFixedBlock(Position position) {
        return rowAt(position.getRow()).blockAt(position.getColumn()) != Block.FIXED;
    }

    private boolean isInsideField(Position position) {
        return position.getRow() >= 0 && position.getColumn() >= 0 && position.getColumn() < FieldRow.DEFAULT_WIDTH;
    }

    private void placeCurrentTetromino() {
        currentTetromino.positionsOnField().forEach(position ->
                changeRow(position, FieldRow::becomesMovingBlock));
    }

    private void removeCurrentTetromino() {
        currentTetromino.positionsOnField().forEach(position ->
                changeRow(position, FieldRow::becomesEmptyBlock));

    }

    private void fixTetromino() {
        removeCurrentTetromino();
        currentTetromino.positionsOnField().forEach(position ->
                changeRow(position, FieldRow::becomesFixedBlock));
    }

    private void changeRow(Position position, BiFunction<FieldRow, Integer, FieldRow> changeRow) {
        FieldRow row = changeRow.apply(rowAt(position.getRow()), position.getColumn());
        rows.set(position.getRow(), row);
    }
}
