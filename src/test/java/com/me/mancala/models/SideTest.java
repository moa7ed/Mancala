package com.me.mancala.models;

import com.me.mancala.gameplay.Fixtures;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SideTest {
    @Test
    public void testEmptySide() {
        Side side = Fixtures.testSide();

        // Side looks like |6|6|6|6|6|6| (0)
        Assert.assertFalse(side.isEmpty());

        side.getPit(0).setStones(0);
        side.getPit(1).setStones(0);
        side.getPit(2).setStones(0);
        side.getPit(3).setStones(0);
        side.getPit(4).setStones(0);
        side.getPit(5).setStones(0);
        side.getLargePit().setStones(5);
        // now Side looks like |6|6|6|6|6|6| (5)
        Assert.assertTrue(side.isEmpty());
    }
}
