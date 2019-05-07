package com.kata.tetris.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    void should_score_100_for_each_line_removed() {
        Score score = new Score();
        score.increaseScoreFor(3);
        assertThat(score.toString()).isEqualTo("300");
    }

    @Test
    void should_score_400_if_4_lines_removed() {
        Score score = new Score();
        score.increaseScoreFor(4);
        assertThat(score.toString()).isEqualTo("800");
    }

}