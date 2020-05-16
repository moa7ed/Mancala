package com.me.mancala.gameplay;

import java.util.UUID;

public class Move {
    private final UUID gameId;
    private final int sideIndex;
    private final int pitIndex;

    public Move(UUID gameId, int sideIndex, int pitIndex) {
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

    public UUID getGameId() {
        return gameId;
    }
}
