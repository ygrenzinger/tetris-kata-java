package com.kata.tetris.ui;

import com.kata.tetris.domain.Score;
import com.kata.tetris.domain.field.Field;

public interface TetrisUI {

    void updateUI(Field field, Score score);

}
