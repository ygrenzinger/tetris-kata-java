package com.kata.tetris.infra;

import com.kata.tetris.domain.tetromino.Orientation;
import com.kata.tetris.domain.tetromino.Shape;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kata.tetris.domain.tetromino.ShapeTest.assertShapeForOrientation;
import static com.kata.tetris.domain.tetromino.ShapeType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShapeLoaderTest {

    private ShapeLoader shapeLoader = new ShapeLoader();

    @Test
    void should_load_I_shape() {
        String filename = "I.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(I);
        assertThat(shape.getSize()).isEqualTo(4);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                "    \n",
                "xxxx\n",
                "    \n",
                "    \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                "  x \n",
                "  x \n",
                "  x \n",
                "  x \n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "    \n",
                "    \n",
                "xxxx\n",
                "    \n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                " x  \n",
                " x  \n",
                " x  \n",
                " x  \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    @Test
    void should_load_J_shape() {
        String filename = "J.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(J);
        assertThat(shape.getSize()).isEqualTo(3);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                "x  \n",
                "xxx\n",
                "   \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                " xx\n",
                " x \n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "   \n",
                "xxx\n",
                "  x\n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                " x \n",
                " x \n",
                "xx \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    @Test
    void should_load_L_shape() {
        String filename = "L.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(L);
        assertThat(shape.getSize()).isEqualTo(3);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                "  x\n",
                "xxx\n",
                "   \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                " x \n",
                " x \n",
                " xx\n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "   \n",
                "xxx\n",
                "x  \n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                "xx \n",
                " x \n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    @Test
    void should_load_O_shape() {
        String filename = "O.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(O);
        assertThat(shape.getSize()).isEqualTo(4);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] shapeToString = {
                " xx \n",
                " xx \n",
                "    \n",
                "    \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, shapeToString);
        assertShapeForOrientation(shape, Orientation.EAST, shapeToString);
        assertShapeForOrientation(shape, Orientation.SOUTH, shapeToString);
        assertShapeForOrientation(shape, Orientation.WEST, shapeToString);
    }

    @Test
    void should_load_S_shape() {
        String filename = "S.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(S);
        assertThat(shape.getSize()).isEqualTo(3);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                " xx\n",
                "xx \n",
                "   \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                " x \n",
                " xx\n",
                "  x\n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "   \n",
                " xx\n",
                "xx \n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                "x  \n",
                "xx \n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    @Test
    void should_load_Z_shape() {
        String filename = "Z.json";
        Shape shape = loadShape(filename);
        assertThat(shape.getType()).isEqualTo(Z);
        assertThat(shape.getSize()).isEqualTo(3);
        assertThat(shape.getShapeByOrientations()).hasSize(4);
        String[] northShape = {
                "xx \n",
                " xx\n",
                "   \n"
        };
        assertShapeForOrientation(shape, Orientation.NORTH, northShape);
        String[] eastShape = {
                "  x\n",
                " xx\n",
                " x \n"
        };
        assertShapeForOrientation(shape, Orientation.EAST, eastShape);
        String[] southShape = {
                "   \n",
                "xx \n",
                " xx\n"
        };
        assertShapeForOrientation(shape, Orientation.SOUTH, southShape);
        String[] westShape = {
                " x \n",
                "xx \n",
                "x  \n"
        };
        assertShapeForOrientation(shape, Orientation.WEST, westShape);
    }

    @Test
    void should_load_T_shape() {
        String filename = "T.json";
        Shape shape = loadShape(filename);
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

    @Test
    void should_throw_an_exception_when_file_does_not_exist() {
        assertThrows(RuntimeException.class, () -> shapeLoader.loadShape(Paths.get("")));
    }

    private Shape loadShape(String filename) {
        try {
            return shapeLoader.loadShape(Paths.get(Shape.class.getResource(filename).toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}