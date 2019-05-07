package com.kata.tetris.infra;

import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.ShapeType;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShapeFactory {

    private ShapeLoader shapeLoader = new ShapeLoader();

    Map<ShapeType, Shape> loadShapes() {
        return Stream.of(ShapeType.values())
                .map(shapeLoader::loadShape)
                .collect(Collectors.toMap(Shape::getType, Function.identity()));
    }

}
