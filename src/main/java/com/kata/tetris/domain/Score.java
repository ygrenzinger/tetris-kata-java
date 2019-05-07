package com.kata.tetris.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Score {
    private AtomicInteger value;

    public Score() {
        this.value= new AtomicInteger(0);
    }

    public void increaseScoreFor(int nbOfLineRemoved) {
        int old = value.get();
        if (nbOfLineRemoved == 4) {
            value.set(old + 800);
        } else {
            value.set(old + (nbOfLineRemoved * 100));
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value.get());
    }
}
