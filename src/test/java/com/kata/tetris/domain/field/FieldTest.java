package com.kata.tetris.domain.field;


import com.kata.tetris.domain.tetromino.Shape;
import com.kata.tetris.domain.tetromino.ShapeTest;
import com.kata.tetris.domain.tetromino.TetrominoState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.kata.tetris.domain.field.Field.DEFAULT_HEIGHT;
import static com.kata.tetris.domain.field.FieldRow.DEFAULT_WIDTH;
import static org.assertj.core.api.Assertions.assertThat;

class FieldTest {

    private Shape shapeT = ShapeTest.createShapeT();
    private Shape shapeI = ShapeTest.createShapeI();

    private Field field;

    @BeforeEach
    void before() {
        field = new Field();
    }

    @Test
    void should_have_24_lines_by_default() {
        assertThat(field.height()).isEqualTo(24);
        assertThat(field.allBlocks()).hasSize(240);
    }

    @Test
    void should_place_some_tetromino_at_top() {
        field.createNewTetrominoAtTop(shapeT);

        assertThat(field.blockAt(23, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 3)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 5)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_rotate_some_tetromino() {
        field.createNewTetrominoAtTop(shapeT);
        field.rotateTetromino();

        assertThat(field.blockAt(22, 3)).isEqualTo(Block.EMPTY);

        assertThat(field.blockAt(23, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 5)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 4)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_move_down_some_tetromino() {
        field.createNewTetrominoAtTop(shapeT);
        TetrominoState state = field.moveDownTetromino();
        assertThat(state).isEqualTo(TetrominoState.MOVING);

        assertThat(field.blockAt(23, 4)).isEqualTo(Block.EMPTY);
        assertThat(field.blockAt(22, 3)).isEqualTo(Block.EMPTY);
        assertThat(field.blockAt(22, 5)).isEqualTo(Block.EMPTY);

        assertThat(field.blockAt(22, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 3)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 5)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_move_some_tetromino_until_reaching_the_ground() {
        field.createNewTetrominoAtTop(shapeT);
        int numberOfMoves = 0;
        while (field.moveDownTetromino() != TetrominoState.REACHED_GROUND) {
            numberOfMoves++;
        }

        assertThat(numberOfMoves).isEqualTo(22);
        assertThat(field.blockAt(1, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(0, 3)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(0, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(0, 5)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_move_some_tetromino_to_the_left() {
        field.createNewTetrominoAtTop(shapeT);
        field.moveLeftTetromino();

        assertThat(field.blockAt(23, 4)).isEqualTo(Block.EMPTY);
        assertThat(field.blockAt(22, 5)).isEqualTo(Block.EMPTY);

        assertThat(field.blockAt(23, 3)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 2)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 3)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 4)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_not_move_some_tetromino_passed_the_left_limits_of_the_field() {
        Shape shape = ShapeTest.createShapeI();
        field.createNewTetrominoAtTop(shape);

        field.rotateTetromino();
        IntStream.range(0, 10).forEach(i -> field.moveLeftTetromino());

        assertThat(field.blockAt(23, 0)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 0)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 0)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(20, 0)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_move_some_tetromino_to_the_right() {
        field.createNewTetrominoAtTop(shapeT);
        field.moveRightTetromino();

        assertThat(field.blockAt(23, 4)).isEqualTo(Block.EMPTY);
        assertThat(field.blockAt(22, 3)).isEqualTo(Block.EMPTY);

        assertThat(field.blockAt(23, 5)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 4)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 5)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 6)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_not_move_some_tetromino_passed_the_right_limits_of_the_field() {
        field.createNewTetrominoAtTop(shapeI);

        field.rotateTetromino();
        IntStream.range(0, 10).forEach(i -> field.moveRightTetromino());

        assertThat(field.blockAt(23, 9)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(22, 9)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 9)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(20, 9)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_do_left_wall_kick_when_rotating_if_possible() {
        field.createNewTetrominoAtTop(shapeI);

        field.rotateTetromino();
        IntStream.range(0, 10).forEach(i -> field.moveLeftTetromino());

        field.rotateTetromino();

        assertThat(field.blockAt(21, 0)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 1)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 2)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 3)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_do_right_wall_kick_when_rotating_if_possible() {
        field.createNewTetrominoAtTop(shapeI);

        field.rotateTetromino();
        IntStream.range(0, 10).forEach(i -> field.moveRightTetromino());

        field.rotateTetromino();

        assertThat(field.blockAt(21, 6)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 7)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 8)).isEqualTo(Block.MOVING);
        assertThat(field.blockAt(21, 9)).isEqualTo(Block.MOVING);
    }

    @Test
    void should_fix_tetromino_which_have_reached_the_ground() {
        //given
        field.createNewTetrominoAtTop(shapeT);
        IntStream.range(0, DEFAULT_HEIGHT).forEach(i -> field.moveDownTetromino());

        //when
        int nbOfLinesRemoved = field.moveDownTetrominoAndFixIfReachedTheGround(() -> shapeI);

        //then
        assertThat(nbOfLinesRemoved).isEqualTo(0);
        assertThat(field.blockAt(1, 4)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(0, 3)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(0, 4)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(0, 5)).isEqualTo(Block.FIXED);
    }

    @Test
    void should_fix_tetromino_which_have_reached_fixed_blocks() {
        //given
        field.createNewTetrominoAtTop(shapeI);
        field.rotateTetromino();
        IntStream.range(0, DEFAULT_HEIGHT).forEach(i -> field.moveDownTetromino());
        field.moveDownTetrominoAndFixIfReachedTheGround(() -> shapeT);
        IntStream.range(0, DEFAULT_HEIGHT).forEach(i -> field.moveDownTetromino());

        //when
        field.moveDownTetrominoAndFixIfReachedTheGround(() -> shapeT);

        //then
        assertThat(field.blockAt(5, 4)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(4, 3)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(4, 4)).isEqualTo(Block.FIXED);
        assertThat(field.blockAt(4, 5)).isEqualTo(Block.FIXED);
    }

    @Test
    void should_remove_full_lines_and_return_the_number_of_lines() {

        //given the 4 first lines except first column
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < DEFAULT_WIDTH; j++) {
                field.becomesFixedBlock(i,j);
            }
        }
        //and I shape down into the empty column
        field.createNewTetrominoAtTop(shapeI);
        field.rotateTetromino();
        IntStream.range(0, DEFAULT_WIDTH).forEach(i -> field.moveLeftTetromino());
        IntStream.range(0, DEFAULT_HEIGHT).forEach(i -> field.moveDownTetromino());

        //when fixing on the ground
        int nbOfLinesRemoved = field.moveDownTetrominoAndFixIfReachedTheGround(() -> shapeI);

        //then
        assertThat(field.height()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(nbOfLinesRemoved).isEqualTo(4);
    }
    
}