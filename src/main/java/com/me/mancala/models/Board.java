package com.me.mancala.models;

import javax.annotation.Nonnull;

public class Board {
    @Nonnull
    private final Side upperSide;
    @Nonnull
    private final Side lowerSide;
    public static final int PITS_PER_SIDE = 6;

    public Board(@Nonnull Side lowerSide, @Nonnull Side upperSide) {
        this.lowerSide = lowerSide;
        this.upperSide = upperSide;
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
        return oppositeSide(pit.getSide()).getPit(PITS_PER_SIDE - pit.getIndex() - 1);
    }

    public Pit nextPit(Player player, Pit pit) {
        Pit toPit = pit;
        if (toPit instanceof LargePit) {
            toPit = this.oppositeSide(toPit.getSide()).getPit(0);
        } else {
            toPit = toPit.getSide().getPit(toPit.getIndex() + 1);
        }
        // other player Mancala (skip)
        if (toPit instanceof LargePit && toPit.getSide().getPlayer() != player) {
            return nextPit(player, toPit);
        }
        return toPit;
    }


}
