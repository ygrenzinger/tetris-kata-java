package com.kata.tetris.domain.tetromino;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.kata.tetris.domain.tetromino.ShapeType.T;
import static org.assertj.core.api.Assertions.assertThat;

public class ShapeTest {

    @Test
    void should_create_shape() {
        Shape shape = createShapeT();
        assertThat(shape.getType()).isEqualTo(T);
        assertThat(shape.getSize()).isEqualTo(3);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                " x \n",
                "xxx\n",
                "   \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                " x \n",
                " xx\n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "   \n",
                "xxx\n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                " x \n",
                "xx \n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    public static void assertShapeForOrientation(Shape shape, Orientation north, String[] expected) {
        String expectedString = String.join("", expected);
        assertThat(shape.shapeForOrientationToString(north)).isEqualTo(expectedString);
    }

    public static Shape createShapeT() {
        Map<Orientation, boolean[][]> shapeByOrientations = new HashMap<>();
        boolean[][] northShape = {
                {false, true, false},
                {true, true, true},
                {false, false, false}
        };
        shapeByOrientations.put(Orientation.NORTH, northShape);
        boolean[][] eastShape = {
                {false, true, false},
                {false, true, true},
                {false, true, false}
        };
        shapeByOrientations.put(Orientation.EAST, eastShape);
        boolean[][] southShape = {
                {false, false, false},
                {true, true, true},
                {false, true, false}
        };
        shapeByOrientations.put(Orientation.SOUTH, southShape);
        boolean[][] westShape = {
                {false, true, false},
                {true, true, false},
                {false, true, false}
        };
        shapeByOrientations.put(Orientation.WEST, westShape);
        return new Shape(ShapeType.T, 3, shapeByOrientations);
    }

    public static Shape createShapeI() {
        Map<Orientation, boolean[][]> shapeByOrientations = new HashMap<>();
        boolean[][] northShape = {
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false},
                {false, false, false, false}
        };
        shapeByOrientations.put(Orientation.NORTH, northShape);
        boolean[][] eastShape = {
                {false, false, true, false},
                {false, false, true, false},
                {false, false, true, false},
                {false, false, true, false}
        };
        shapeByOrientations.put(Orientation.EAST, eastShape);
        boolean[][] southShape = {
                {false, false, false, false},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };
        shapeByOrientations.put(Orientation.SOUTH, southShape);
        boolean[][] westShape = {
                {false, true, false, false},
                {false, true, false, false},
                {false, true, false, false},
                {false, true, false, false}
        };
        shapeByOrientations.put(Orientation.WEST, westShape);
        return new Shape(ShapeType.I, 4, shapeByOrientations);
    }

}