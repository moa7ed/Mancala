package com.me.mancala.gameplay.models;

public class Move {
    private final int sideIndex;
    private final int pitIndex;

    public Move(int sideIndex, int pitIndex) {
        this.sideIndex = sideIndex;
        this.pitIndex = pitIndex;
    }

    public int getSideIndex() {
        return sideIndex;
    }

    public int getPitIndex() {
        return pitIndex;
    }

}
