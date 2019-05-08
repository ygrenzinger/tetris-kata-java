package com.kata.tetris.domain;

import com.kata.tetris.domain.field.Field;

public interface UpdateUI {

    void updateUI(Field field, Score score, boolean gameOver);

}
