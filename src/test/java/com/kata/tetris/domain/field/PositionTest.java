package com.kata.tetris.domain.field;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void should_compare_equality_with_row_and_column() {
        Position position = new Position(1, 2);
        assertThat(position.correspondsTo(position.getRow(), position.getColumn())).isTrue();
    }

}