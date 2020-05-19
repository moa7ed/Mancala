package com.me.mancala.models;

import javax.annotation.Nonnull;

public class Board {
    @Nonnull
    private final Side upperSide;
    @Nonnull
    private final Side lowerSide;
    private final int pitsPerSide;

    public Board(@Nonnull Side lowerSide, @Nonnull Side upperSide, int pitsPerSide) {
        this.lowerSide = lowerSide;
        this.upperSide = upperSide;
        this.pitsPerSide = pitsPerSide;
    }

    @Nonnull
    public Side getUpperSide() {
        return upperSide;
    }

    @Nonnull
    public Side getLowerSide() {
        return lowerSide;
    }

    @Nonnull
    public Side getSide(int sideIndex) throws IllegalAccessException {
        if (sideIndex == 0) {
            return lowerSide;
        } else if (sideIndex == 1) {
            return upperSide;
        } else {
            throw new IllegalAccessException(String.format("%d is not a valid index for side", sideIndex));
        }
    }

    @Nonnull
    public Side oppositeSide(@Nonnull Side side) {
        if (side == lowerSide) {
            return upperSide;
        } else {
            return lowerSide;
        }
    }

    @Nonnull
    public Pit oppositePit(@Nonnull Pit pit)  {
        if (pit instanceof LargePit) {
            throw new RuntimeException("This function only accept smallPit");
        }
        return oppositeSide(pit.getSide()).getPit(this.pitsPerSide - pit.getIndex() - 1);
    }

}
