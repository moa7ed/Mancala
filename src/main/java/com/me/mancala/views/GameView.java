package com.me.mancala.views;

import com.me.mancala.models.Game;
import io.dropwizard.views.View;


public class GameView extends View {
    private final Game game;

    public GameView(Game game) {
        super("game.ftl");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
