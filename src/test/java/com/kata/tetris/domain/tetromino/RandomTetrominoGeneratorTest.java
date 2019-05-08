package com.kata.tetris.domain.tetromino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RandomTetrominoGeneratorTest {

    ShapeFactory mockShapeFactory;
    RandomTetrominoGenerator generator;

    @BeforeEach
    void before() {
        mockShapeFactory = Mockito.mock(ShapeFactory.class);
        Mockito.when(mockShapeFactory.allShapes()).thenReturn(
                Stream.of(ShapeTest.createShapeT(), ShapeTest.createShapeI())
                        .collect(Collectors.toMap(Shape::getType, shape -> shape)));
        generator = new RandomTetrominoGenerator(mockShapeFactory);
    }

    @Test
    void should_first_fill_bags_of_tetrimino() {
        Mockito.verify(mockShapeFactory, Mockito.times(1)).allShapes();
    }

    @Test
    void should_return_a_sequence_of_shapes_permuted_randomly() {
        assertThat(generator.randomShape()).isIn(ShapeTest.createShapeI(), ShapeTest.createShapeT());
        assertThat(generator.randomShape()).isIn(ShapeTest.createShapeI(), ShapeTest.createShapeT());
    }

    @Test
    void should_refill_bag_when_empty() {
        for (int i = 0; i < 3; i++) {
            generator.randomShape();
        }
        Mockito.verify(mockShapeFactory, Mockito.times(2)).allShapes();
    }
}