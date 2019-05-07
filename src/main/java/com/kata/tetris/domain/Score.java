package com.kata.tetris.domain;

public class Score {
    private int value;

    private Score(int value) {
        this.value = value;
    }

    public Score() {
        this(0);
    }

    public Score increaseScoreFor(int nbOfLineRemoved) {
        if (nbOfLineRemoved == 4) {
            return new Score(value + 800);
        } else {
            return new Score(value + (nbOfLineRemoved * 100));
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
