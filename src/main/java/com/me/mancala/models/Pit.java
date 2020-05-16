package com.me.mancala.models;

public abstract class Pit {
    private final int index;
    private final Side side;
    protected int stones;

    public Pit(int stones, Side side, int index) {
        this.stones = stones;
        this.side = side;
        this.index = index;
    }

    public int getStones() {
        return stones;
    }

    public Side getSide() {
        return side;
    }

    public void addStones(int extraStones) {
        this.stones += stones;
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
}
