package com.me.mancala.models;

import com.me.mancala.gameplay.Fixtures;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = Fixtures.testBoard();
    }

    @Test
    public void testGetSide() throws IllegalAccessException {
        Assert.assertEquals(board.getLowerSide(), board.getSide(0));
        Assert.assertEquals(board.getUpperSide(), board.getSide(1));

        IllegalAccessException thrown = assertThrows(
                IllegalAccessException.class,
                () -> board.getSide(2),
                "Exception should be thrown"
        );
        Assert.assertTrue(thrown.getMessage().contains("not a valid index for side"));
    }

    @Test
    public void testOppositeSide() {
        Assert.assertEquals(board.getLowerSide(), board.oppositeSide(board.getUpperSide()));
        Assert.assertEquals(board.getUpperSide(), board.oppositeSide(board.getLowerSide()));
    }

    @Test
    public void testOppositePit() {
        Assert.assertEquals(board.getUpperSide().getPit(0), board.oppositePit(board.getLowerSide().getPit(5)));
        Assert.assertEquals(board.getLowerSide().getPit(3), board.oppositePit(board.getUpperSide().getPit(2)));

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> board.oppositePit(board.getLowerSide().getLargePit()),
                "Exception should be thrown"
        );
        Assert.assertTrue(thrown.getMessage().contains("This function only accept smallPit"));
    }

}
