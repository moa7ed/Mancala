package com.me.mancala.models;

import javax.annotation.Nonnull;

public class SmallPit extends Pit {
    public static final int STARTING_STONES = 6;

    public SmallPit(@Nonnull Side side, int index) {
        super(STARTING_STONES, side, index);
    }

}
