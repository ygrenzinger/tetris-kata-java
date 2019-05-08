package com.kata.tetris.domain;

import com.kata.tetris.domain.field.Field;
import com.kata.tetris.domain.tetromino.RandomTetrominoGenerator;
import com.kata.tetris.domain.tetromino.ShapeFactory;
import com.kata.tetris.domain.tetromino.ShapeTest;
import com.kata.tetris.infra.FileShapeLoader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import static org.assertj.core.api.Assertions.assertThat;

class TetrisTest {

    private FileShapeLoader shapeLoader = new FileShapeLoader();
    private ShapeFactory shapeFactory = new ShapeFactory(shapeLoader);
    private RandomTetrominoGenerator generator = new RandomTetrominoGenerator(shapeFactory);

    @Test
    void playing_tetris_until_gameover() {
        UpdateUI updateUIMock = Mockito.mock(UpdateUI.class);
        Field field = Mockito.spy(new Field());
        Score score = Mockito.spy(new Score());
        Tetris tetris = new Tetris(updateUIMock, () -> ShapeTest.createShapeT(), field, score);
        tetris.startGame();
        while (tetris.isOnGoingGame()) {
            Command.DOWN.applyOn(tetris);
            tetris.updateGame();
        }
        assertThat(tetris.isGameOver()).isTrue();

        Mockito.verify(updateUIMock, Mockito.atLeast(10)).updateUI(Mockito.any(), Mockito.any(), Mockito.anyBoolean());
        Mockito.verify(field, Mockito.atLeast(10)).moveTetrominoDown();
        Mockito.verify(score, Mockito.atLeast(10)).increaseScoreFor(Mockito.anyInt());
    }
    
}