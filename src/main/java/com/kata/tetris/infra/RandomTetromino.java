package com.kata.tetris.infra;

import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.ShapeType;

import java.util.Map;
import java.util.Random;

public class RandomTetromino {

    private final Map<ShapeType, Shape> shapes;
    private final Random random;

    public RandomTetromino() {
        ShapeFactory shapeFactory = new ShapeFactory();
        shapes = shapeFactory.loadShapes();
        random = new Random();
    }

    public Shape randomShape() {
        random.setSeed(System.nanoTime());
        return shapeFrom(random.nextInt(ShapeType.values().length));
    }

    private Shape shapeFrom(int i) {
        for (ShapeType type : ShapeType.values()) {
            if (type.ordinal() == i) {
                return shapes.get(type);
            }
        }
        return shapes.get(0);
    }
}
