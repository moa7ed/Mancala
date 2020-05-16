package com.me.mancala;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;


public class MainConfiguration extends Configuration {
    private String defaultName = "Stranger";

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
