package com.me.mancala.resources.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class CreateGameRequest {
    @Nonnull
    @JsonProperty("upper_player")
    private String upperPlayer;

    @Nonnull
    @JsonProperty("lower_player")
    private String lowerPlayer;

    @JsonCreator
    public CreateGameRequest(
            @Nonnull @JsonProperty("upper_player") String upperPlayer,
            @Nonnull @JsonProperty("lower_player") String lowerPlayer
    ) {
        this.upperPlayer = upperPlayer;
        this.lowerPlayer = lowerPlayer;
    }

    @Nonnull
    public String getUpperPlayer() {
        return upperPlayer;
    }

    @Nonnull
    public String getLowerPlayer() {
        return lowerPlayer;
    }

}
