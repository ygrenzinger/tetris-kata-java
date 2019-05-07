package com.kata.tetris.domain.tetromino;

import org.junit.jupiter.api.Test;

import static com.kata.tetris.domain.tetromino.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @Test
    void should_rotate_through_all_orientation() {
        Orientation orientation = NORTH;
        orientation = orientation.next();
        assertThat(orientation).isEqualTo(EAST);
        orientation = orientation.next();
        assertThat(orientation).isEqualTo(SOUTH);
        orientation = orientation.next();
        assertThat(orientation).isEqualTo(WEST);
        orientation = orientation.next();
        assertThat(orientation).isEqualTo(NORTH);
    }

}