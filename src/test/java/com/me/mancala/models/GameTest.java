package com.me.mancala.models;

import com.me.mancala.gameplay.Fixtures;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void testNextPlayerTurn() {
        Game game = Fixtures.testGame();
        Player lower = game.getBoard().getLowerSide().getPlayer();
        Player upper = game.getBoard().getUpperSide().getPlayer();

        game.nextPlayerTurn();
        Assert.assertEquals(upper, game.getCurrentPlayer());

        game.nextPlayerTurn();
        Assert.assertEquals(lower, game.getCurrentPlayer());
    }
}
