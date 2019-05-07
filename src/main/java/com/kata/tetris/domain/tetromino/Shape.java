package com.kata.tetris.domain.tetromino;


import java.util.Map;

public class Shape {
    private final ShapeType type;
    private final Integer size;
    private final Map<Orientation, boolean[][]> shapeByOrientations;

    public Shape(ShapeType type, Integer size, Map<Orientation, boolean[][]> shapeByOrientations) {
        this.type = type;
        this.size = size;
        this.shapeByOrientations = shapeByOrientations;
    }

    public ShapeType getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }

    public Map<Orientation, boolean[][]> getShapeByOrientations() {
        return shapeByOrientations;
    }

    String shapeForOrientationToString(Orientation orientation) {
        boolean[][] shape = shapeByOrientations.get(orientation);
        StringBuilder builder = new StringBuilder();
        for (boolean[] row : shape) {
            for (boolean block : row) {
                if (block) {
                    builder.append('x');
                } else {
                    builder.append(' ');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    int leftWallKickPossibleShift(Orientation orientation) {
        boolean[][] shape = shapeByOrientations.get(orientation);
        int shift = 0;
        while (emptyColumn(shape, shift)) {
            shift++;
        }
        return shift;
    }

    int rightWallKickPossibleShift(Orientation orientation) {
        boolean[][] shape = shapeByOrientations.get(orientation);
        int shift = 0;
        while (emptyColumn(shape, (size - 1) - shift)) {
            shift++;
        }
        return shift;
    }

    private boolean emptyColumn(boolean[][] shape, int column) {
        for (boolean[] row : shape) {
            if (row[column]) {
                return false;
            }
        }
        return true;
    }
}
