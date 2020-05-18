package com.me.mancala.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

public class Player {
    @JsonProperty("name")
    @Nonnull
    private final String name;

    public Player(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }
}
