package com.kata.tetris.domain;

import com.kata.tetris.domain.field.Field;

import java.util.function.Consumer;

public enum Command {
    ROTATE(Field::rotateTetromino),
    LEFT(Field::moveTetrominoLeft),
    RIGHT(Field::moveTetrominoRight),
    DOWN(Field::moveTetrominoDown);
    private final Consumer<Field> action;

    Command(Consumer<Field> action) {
        this.action = action;
    }

    public void applyOn(Tetris tetris) {
        tetris.apply(this);
    }

    public void applyOn(Field field) {
        action.accept(field);
    }
}
