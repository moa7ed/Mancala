package com.me.mancala.views;

import io.dropwizard.views.View;

import javax.annotation.Nonnull;
import java.util.List;

public class GameListView extends View {
    @Nonnull
    private final List<String> games;

    public GameListView(@Nonnull List<String> games) {
        super("gameList.ftl");
        this.games = games;
    }

    @Nonnull
    public List<String> getGames() {
        return games;
    }
}
