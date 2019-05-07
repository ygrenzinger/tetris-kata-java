package com.kata.tetris.domain.tetromino;

import com.kata.tetris.domain.field.Position;

import java.util.ArrayList;
import java.util.List;

public class Tetromino {
    private final Shape shape;

    private final Orientation orientation;

    //Top left position
    private final int rowPosition;
    private final int columnPosition;
    private final List<Position> positions;

    private Tetromino(Shape shape, int rowPosition, int columnPosition, Orientation orientation) {
        this.shape = shape;
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.orientation = orientation;
        this.positions = computeCurrentPositions(shape, rowPosition, columnPosition, orientation);
    }

    public Tetromino(Shape shape, int rowPosition, int columnPosition) {
        this(shape, rowPosition, columnPosition, Orientation.NORTH);
    }

    public List<Position> positionsOnField() {
        return positions;
    }

    public Tetromino rotate() {
        return new Tetromino(shape, rowPosition, columnPosition, orientation.next());
    }

    public Tetromino leftWallKick(Tetromino tetromino) {
        Tetromino future = tetromino;
        for (int i = 0; i < shape.leftWallKickPossibleShift(orientation); i++) {
            future = future.moveRight();
        }
        return future.rotate();
    }

    public Tetromino rightWallKick(Tetromino tetromino) {
        Tetromino future = tetromino;
        for (int i = 0; i < shape.rightWallKickPossibleShift(orientation); i++) {
            future = future.moveLeft();
        }
        return future.rotate();
    }

    public Tetromino moveLeft() {
        return new Tetromino(shape, rowPosition, columnPosition - 1, orientation);
    }

    public Tetromino moveRight() {
        return new Tetromino(shape, rowPosition, columnPosition + 1, orientation);
    }

    public Tetromino moveDown() {
        return new Tetromino(shape, rowPosition - 1, columnPosition, orientation);
    }

    private List<Position> computeCurrentPositions(Shape shape, int rowPosition, int columnPosition, Orientation orientation) {
        boolean[][] blocks = shape.getShapeByOrientations().get(orientation);
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < shape.getSize(); i++) {
            for (int j = 0; j < shape.getSize(); j++) {
                if (blocks[i][j]) {
                    int row = rowPosition - i;
                    int column = columnPosition + j;
                    positions.add(new Position(row, column));
                }
            }
        }
        return positions;
    }
}
