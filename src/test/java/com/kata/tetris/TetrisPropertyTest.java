package com.kata.tetris;

import java.util.*;

import com.kata.tetris.domain.Command;
import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.Tetris;
import com.kata.tetris.domain.UpdateUI;
import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.RandomTetrominoGenerator;
import com.kata.tetris.domain.tetromino.ShapeFactory;
import com.kata.tetris.infra.FileShapeLoader;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class TetrisPropertyTest {

    private FileShapeLoader shapeLoader = new FileShapeLoader();
    private ShapeFactory shapeFactory = new ShapeFactory(shapeLoader);
    private RandomTetrominoGenerator generator = new RandomTetrominoGenerator(shapeFactory);

    @Property
    void playing_tetris_until_gameover(@ForAll @Size(1000) List<Command> commands) {
        UpdateUI updateUIMock = Mockito.mock(UpdateUI.class);
        Tetris tetris = new Tetris(updateUIMock, generator::randomShape, new Field(), new Score());
        tetris.startGame();
        assertThatGameIsOverOrOnGoing(tetris);
        commands.forEach(command -> {
            command.applyOn(tetris);
            tetris.updateGame();
            assertThatGameIsOverOrOnGoing(tetris);
        });
    }

    private void assertThatGameIsOverOrOnGoing(Tetris tetris) {
        if (tetris.isGameOver()) {
            assertThat(tetris.isOnGoingGame()).isFalse();
        } else {
            assertThat(tetris.isOnGoingGame()).isTrue();
        }
    }
}