package com.me.mancala.models;

public class Board {
    private final Side upperSide;
    private final Side lowerSide;

    public Board(Side upperSide, Side lowerSide) {
        this.upperSide = upperSide;
        this.lowerSide = lowerSide;
    }

    public Side getUpperSide() {
        return upperSide;
    }

    public Side getLowerSide() {
        return lowerSide;
    }
}
