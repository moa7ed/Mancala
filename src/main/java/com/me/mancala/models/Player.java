package com.me.mancala.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonProperty("name")
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
