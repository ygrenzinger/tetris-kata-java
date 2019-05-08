package com.kata.tetris.domain.tetromino;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Following wiki https://tetris.fandom.com/wiki/Random_Generator
public class RandomTetrominoGenerator {

    private final ShapeFactory shapeFactory;
    private final Random random;
    private final List<Shape> bagOfShapes = new LinkedList<>();

    public RandomTetrominoGenerator(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        this.random = new Random();
        fillBagOfShapes();
    }

    private void fillBagOfShapes() {
        Map<ShapeType, Shape> shapeTypeShapeMap = shapeFactory.allShapes();
        bagOfShapes.addAll(shapeTypeShapeMap.values());
    }

    public Shape randomShape() {
        if (bagOfShapes.isEmpty()) {
            fillBagOfShapes();
        }
        random.setSeed(System.nanoTime());
        return bagOfShapes.remove(random.nextInt(bagOfShapes.size()));
    }
}
