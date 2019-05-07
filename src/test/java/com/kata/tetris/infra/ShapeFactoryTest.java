package com.kata.tetris.infra;

import com.kata.tetris.domain.field.Block;
import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.ShapeType;
import com.kata.tetris.infra.ShapeFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ShapeFactoryTest {
    private ShapeFactory shapeFactory = new ShapeFactory();
    private Map<ShapeType, Shape> shapes = shapeFactory.loadShapes();

    @Test
    void should_load_all_tetromino_shapes() {
        ShapeFactory factory = new ShapeFactory();
        Map<ShapeType, Shape> shapes = factory.loadShapes();
        assertThat(shapes).hasSize(7);
        assertThat(shapes).containsOnlyKeys(ShapeType.values());
    }

    @ParameterizedTest
    @EnumSource(ShapeType.class)
    void should_only_have_4_moving_blocks_for_any_shape(ShapeType shapeType) {
        Field field = new Field();
        Shape shape = shapes.get(shapeType);
        field.createNewTetrominoAtTop(shape);
        long numberOfMovingBlocks = field.allBlocks()
                .stream()
                .filter(b -> b == Block.MOVING)
                .count();
        assertThat(numberOfMovingBlocks).isEqualTo(4);
    }

}