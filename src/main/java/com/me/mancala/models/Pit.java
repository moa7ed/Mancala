package com.me.mancala.models;

public abstract class Pit {
    protected int stones;

    public Pit(int stones) {
        this.stones = stones;
    }

    public int getStones() {
        return stones;
    }

    public void addStones(int extraStones) {
        this.stones += stones;
    }

    public boolean isEmpty() {
        return stones == 0;
    }
}
