package com.me.mancala.resources.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me.mancala.models.Player;

import javax.annotation.Nonnull;

public class CreateGameRequest {
    @Nonnull
    @JsonProperty("upper_player")
    private Player upperPlayer;

    @Nonnull
    @JsonProperty("lower_player")
    private Player lowerPlayer;

    public CreateGameRequest(
            @Nonnull @JsonProperty("upper_player") Player upperPlayer,
            @Nonnull @JsonProperty("lower_player") Player lowerPlayer
    ) {
        this.upperPlayer = upperPlayer;
        this.lowerPlayer = lowerPlayer;
    }

    @Nonnull
    public Player getUpperPlayer() {
        return upperPlayer;
    }

    @Nonnull
    public Player getLowerPlayer() {
        return lowerPlayer;
    }

    public void setUpperPlayer(@Nonnull Player upperPlayer) {
        this.upperPlayer = upperPlayer;
    }

    public void setLowerPlayer(@Nonnull Player lowerPlayer) {
        this.lowerPlayer = lowerPlayer;
    }
}
