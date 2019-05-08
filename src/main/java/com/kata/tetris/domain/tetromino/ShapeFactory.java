package com.kata.tetris.domain.tetromino;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShapeFactory {

    private final Map<ShapeType, Shape> shapes;

    public ShapeFactory(ShapeLoader shapeLoader) {
        this.shapes = Collections.unmodifiableMap(loadShapes(shapeLoader));
    }

    private Map<ShapeType, Shape> loadShapes(ShapeLoader shapeLoader) {
        return Stream.of(ShapeType.values())
                .map(shapeLoader::loadShape)
                .collect(Collectors.toMap(Shape::getType, Function.identity()));
    }

    Map<ShapeType, Shape> allShapes() {
        return shapes;
    }
}
