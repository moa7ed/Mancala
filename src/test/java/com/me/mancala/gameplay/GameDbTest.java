package com.me.mancala.gameplay;

import com.me.mancala.models.Game;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GameDbTest {
    private GameDb gameDb;

    @BeforeEach
    public void setup() {
        this.gameDb = GameDb.getInstance();
    }

    @Test
    public void testInsert() {
        Game testGame = Fixtures.testGame();
        this.gameDb.insert(testGame);
        Assert.assertEquals(testGame, this.gameDb.fetch(testGame.getUuid()));
    }
}
