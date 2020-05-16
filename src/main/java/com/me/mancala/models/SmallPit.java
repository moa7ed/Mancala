package com.me.mancala.models;

public class SmallPit extends Pit {
    public static final int STARTING_STONES = 6;

    public SmallPit(Side side, int index) {
        super(STARTING_STONES, side, index);
    }

    public int empty() {
        int stones = this.stones;
        this.stones = 0;
        return stones;
    }


}
