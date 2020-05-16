package com.me.mancala.models;

import java.util.List;

public class Board {
    private final Side upperSide;
    private final Side lowerSide;
    public static final int PITS_PER_SIDE = 6;

    public Board(Side upperSide, Side lowerSide) {
        this.lowerSide = lowerSide;
        this.upperSide = upperSide;
    }

    public Side getUpperSide() {
        return upperSide;
    }

    public Side getLowerSide() {
        return lowerSide;
    }

    public Side getSide(int sideIndex) {
        if (sideIndex == 0) {
            return lowerSide;
        } else if (sideIndex == 1) {
            return upperSide;
        } else {
            throw new IllegalArgumentException(String.format("%d is not a valid index for side", sideIndex));
        }
    }

    public Side oppositeSide(Side side) {
        if (side == lowerSide) {
            return upperSide;
        } else {
            return lowerSide;
        }
    }

    public Pit oppositePit(Pit pit) {
        if (pit instanceof LargePit) {
            throw new IllegalArgumentException("This function only accept smallPit");
        }
        return oppositeSide(pit.getSide()).getPit(PITS_PER_SIDE - pit.getIndex());
    }
}
