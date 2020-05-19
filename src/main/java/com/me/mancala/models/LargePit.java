package com.me.mancala.models;

import javax.annotation.Nonnull;

public class LargePit extends Pit {
    public static final int STARTING_STONES = 0;

    public LargePit(@Nonnull Side side, int index) {
        super(STARTING_STONES, side, index);
    }

}
