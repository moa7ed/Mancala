package com.me.mancala.models;

import javax.annotation.Nonnull;

public abstract class Pit {
    private final int index;
    @Nonnull
    private final Side side;
    protected int stones;

    public Pit(int stones, @Nonnull Side side, int index) {
        this.stones = stones;
        this.side = side;
        this.index = index;
    }

    public int getStones() {
        return stones;
    }

    @Nonnull
    public Side getSide() {
        return side;
    }

    public void addStones(int extraStones) {
        this.stones += extraStones;
    }

    public void removeStones(int stones) {
        this.stones -= stones;
    }

    public int getIndex() {
        return index;
    }

    public boolean isEmpty() {
        return stones == 0;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }
}
