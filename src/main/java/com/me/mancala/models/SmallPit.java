package com.me.mancala.models;

public class SmallPit extends Pit {
    private final int index;
    public static final int STARTING_STONES = 6;

    public SmallPit(int index) {
        super(STARTING_STONES);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int empty() {
        int stones = this.stones;
        this.stones = 0;
        return stones;
    }


}
