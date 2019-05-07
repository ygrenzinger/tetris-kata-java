package com.kata.tetris.domain.field;

import static com.kata.tetris.domain.field.Block.*;

public class FieldRow {
    public static final int DEFAULT_WIDTH = 10;
    private final Block[] blocks;

    private FieldRow(Block[] blocks) {
        this.blocks = blocks;
    }

    private FieldRow(int width) {
        assert width > 0;
        this.blocks = initRow(width);
    }

    FieldRow() {
        this(DEFAULT_WIDTH);
    }

    int width() {
        return blocks.length;
    }

    Block blockAt(int column) {
        return blocks[column];
    }

    Block[] blocks() {
        return blocks;
    }

    FieldRow becomesEmptyBlock(int index) {
        return changeBlock(index, EMPTY);
    }

    FieldRow becomesMovingBlock(int index) {
        return changeBlock(index, MOVING);
    }

    FieldRow becomesFixedBlock(int index) {
        return changeBlock(index, FIXED);
    }

    boolean isFull() {
        for (Block block : blocks) {
            if (block == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private FieldRow changeBlock(int index, Block block) {
        Block[] blocks = this.blocks.clone();
        blocks[index] = block;
        return new FieldRow(blocks);
    }

    private static Block[] initRow(int width) {
        Block[] blocks = new Block[width];
        for (int i = 0; i < width; i++) {
            blocks[i] = EMPTY;
        }
        return blocks;
    }
}
