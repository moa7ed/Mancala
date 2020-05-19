package com.me.mancala.resources.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class GameResponse {
    @Nonnull
    @JsonProperty("game_uuid")
    private String gameUuid;

    public GameResponse(@Nonnull String gameUuid) {
        this.gameUuid = gameUuid;
    }

    @Nonnull
    @JsonProperty("game_uuid")
    public String getGameUuid() {
        return gameUuid;
    }
}
