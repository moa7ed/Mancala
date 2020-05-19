package com.me.mancala.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class Player {
    @JsonProperty("name")
    @Nonnull
    private final String name;

    @JsonCreator
    public Player(@Nonnull @JsonProperty("name") String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }
}
