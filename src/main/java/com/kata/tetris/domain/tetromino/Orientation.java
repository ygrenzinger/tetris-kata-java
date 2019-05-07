package com.kata.tetris.domain.tetromino;

public enum Orientation {
    NORTH, EAST, SOUTH, WEST;

    public Orientation next() {
        Orientation[] values = Orientation.values();
        return values[(this.ordinal()+1)%values.length];
    }
}
