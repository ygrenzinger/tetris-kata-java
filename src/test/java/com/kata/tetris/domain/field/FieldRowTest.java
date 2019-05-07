package com.kata.tetris.domain.field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kata.tetris.domain.field.Block.*;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class FieldRowTest {

    private FieldRow row;

    @BeforeEach
    void before() {
        row = new FieldRow();
    }

    @Test
    void should_have_10_empty_blocks_by_default() {
        assertThat(row.blocks()).hasSize(10);
        assertThat(row.blocks()).containsOnlyElementsOf(singletonList(EMPTY));
    }

    @Test
    void should_becomes_a_moving_block() {
        assertThat(row.becomesMovingBlock(2).blockAt(2)).isEqualTo(MOVING);
    }

    @Test
    void should_becomes_a_fixed_block() {
        assertThat(row.becomesFixedBlock(2).blockAt(2)).isEqualTo(FIXED);
    }

    @Test
    void should_tell_if_line_is_full() {
        assertThat(fillRow().isFull()).isTrue();
    }

    @Test
    void should_tell_if_line_is_not_full() {
        FieldRow newRow = fillRow().becomesEmptyBlock(0);
        assertThat(newRow.isFull()).isFalse();
    }

    private FieldRow fillRow() {
        FieldRow newRow = row;
        for (int i = 0; i < newRow.width(); i++) {
            if (i % 2 == 0) {
                newRow = newRow.becomesFixedBlock(i);
            } else {
                newRow = newRow.becomesMovingBlock(i);
            }
        }
        return newRow;
    }

}