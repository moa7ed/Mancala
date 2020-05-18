package com.me.mancala.gameplay;

import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameInitializerTest {

    @Test
    public void testGameInitializing() {
        Player player1 = Fixtures.testPlayer();
        Player player2 = Fixtures.testPlayer();
        Game testGame = GameInitializer.initializeGame(player1, player2);
        Assert.assertEquals(player1.getName(), testGame.getCurrentPlayer().getName());
        Assert.assertNull(testGame.getWinner());
        Assert.assertFalse(testGame.isGameOver());
        Assert.assertNotNull(testGame.getBoard());
        Assert.assertNotNull(testGame.getUuid());
        Assert.assertNotNull(testGame.getBoard().getLowerSide());
        Assert.assertNotNull(testGame.getBoard().getUpperSide());
    }
}
