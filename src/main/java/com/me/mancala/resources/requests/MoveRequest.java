package com.me.mancala.resources.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me.mancala.models.Player;
import io.dropwizard.validation.MaxSize;
import io.dropwizard.validation.MinSize;

import javax.annotation.Nonnull;
import java.util.UUID;

public class MoveRequest {
    @JsonProperty("game_id")
    private UUID gameId;

    @MaxSize(1)
    @MinSize(0)
    @JsonProperty("side_index")
    private int sideIndex;

    @MaxSize(5)
    @MinSize(0)
    @JsonProperty("pit_index")
    private int pitIndex;

    public MoveRequest(
            @Nonnull @JsonProperty("game_id") UUID gameId,
            @Nonnull @JsonProperty("side_index") int sideIndex,
            @Nonnull @JsonProperty("pit_index") int pitIndex
    ) {
        this.gameId = gameId;
        this.sideIndex = sideIndex;
        this.pitIndex = pitIndex;
    }

    public UUID getGameId() {
        return gameId;
    }

    public int getSideIndex() {
        return sideIndex;
    }

    public int getPitIndex() {
        return pitIndex;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setSideIndex(int sideIndex) {
        this.sideIndex = sideIndex;
    }

    public void setPitIndex(int pitIndex) {
        this.pitIndex = pitIndex;
    }
}
