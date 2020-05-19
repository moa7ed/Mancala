package com.me.mancala.views;

import com.me.mancala.models.Game;
import io.dropwizard.views.View;

import javax.annotation.Nonnull;


public class GameView extends View {
    @Nonnull
    private final Game game;

    public GameView(@Nonnull Game game) {
        super("game.ftl");
        this.game = game;
    }

    @Nonnull
    public Game getGame() {
        return game;
    }
}
