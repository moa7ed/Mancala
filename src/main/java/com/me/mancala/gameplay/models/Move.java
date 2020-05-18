package com.me.mancala.gameplay.models;

import javax.annotation.Nonnull;
import java.util.UUID;

public class Move {
    @Nonnull
    private final UUID gameId;
    private final int sideIndex;
    private final int pitIndex;

    public Move(@Nonnull UUID gameId, int sideIndex, int pitIndex) {
        this.gameId = gameId;
        this.sideIndex = sideIndex;
        this.pitIndex = pitIndex;
    }

    public int getSideIndex() {
        return sideIndex;
    }

    public int getPitIndex() {
        return pitIndex;
    }

    @Nonnull
    public UUID getGameId() {
        return gameId;
    }
}
