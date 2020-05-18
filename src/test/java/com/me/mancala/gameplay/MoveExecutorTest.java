package com.me.mancala.gameplay;

import com.me.mancala.gameplay.models.Move;
import com.me.mancala.gameplay.models.StoneTransition;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveExecutorTest {
    private Game game = Fixtures.testGame();
    private MoveExecutor moveExecutor = new MoveExecutor(game);
    @BeforeEach
    public void setup() {

    }

    @Test
    public void testDistribution() throws IllegalAccessException {
        game.getBoard().getLowerSide().getPit(4).setStones(10);
        /*
        Current game structure
             |6|6|6|6|6|6|
        ( 0 )              ( 0 )
             |6|6|6|6|10|6|
        will play index 4
        Target:
             |7|7|7|7|7|7|
        ( 0 )              ( 1 )
             |7|7|6|6|0|7|
        * */

        Move move = new Move(game.getUuid(), 0, 4);
        List<StoneTransition> stoneTransitions = moveExecutor.applyMove(move);

        Assert.assertEquals(7, game.getBoard().getLowerSide().getPit(0).getStones());
        Assert.assertEquals(7, game.getBoard().getLowerSide().getPit(1).getStones());
        Assert.assertEquals(6, game.getBoard().getLowerSide().getPit(2).getStones());
        Assert.assertEquals(6, game.getBoard().getLowerSide().getPit(3).getStones());
        Assert.assertEquals(0, game.getBoard().getLowerSide().getPit(4).getStones());
        Assert.assertEquals(7, game.getBoard().getLowerSide().getPit(5).getStones());
        Assert.assertEquals(1, game.getBoard().getLowerSide().getLargePit().getStones());

        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(0).getStones());
        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(1).getStones());
        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(2).getStones());
        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(3).getStones());
        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(4).getStones());
        Assert.assertEquals(7, game.getBoard().getUpperSide().getPit(5).getStones());
        Assert.assertEquals(0, game.getBoard().getUpperSide().getLargePit().getStones());

        Assert.assertEquals(10, stoneTransitions.size());
    }

    @Test
    public void testLastPitSpecialCase() throws IllegalAccessException {
        game.getBoard().getLowerSide().getPit(0).setStones(2);
        game.getBoard().getLowerSide().getPit(1).setStones(0);
        game.getBoard().getLowerSide().getPit(2).setStones(0); // <- special case here
        /*
        Current game structure
             |6|6|6|6|6|6|
        ( 0 )              ( 0 )
             |2|0|0|6|6|6|
         will play index 0
        Target:
             |6|6|6|6|6|6|
        ( 0 )              ( 7 )
             |0|1|0|6|6|6|
        * */

        Move move = new Move(game.getUuid(), 0, 0);
        moveExecutor.applyMove(move);

        Assert.assertEquals(0, game.getBoard().getLowerSide().getPit(0).getStones());
        Assert.assertEquals(1, game.getBoard().getLowerSide().getPit(1).getStones());
        Assert.assertEquals(0, game.getBoard().getLowerSide().getPit(2).getStones());
        Assert.assertEquals(6, game.getBoard().getLowerSide().getPit(3).getStones());
        Assert.assertEquals(6, game.getBoard().getLowerSide().getPit(4).getStones());
        Assert.assertEquals(6, game.getBoard().getLowerSide().getPit(5).getStones());
        Assert.assertEquals(7, game.getBoard().getLowerSide().getLargePit().getStones());
    }

    @Test
    public void testUpdateCurrentPlayer() throws IllegalAccessException {
        Player lower = game.getBoard().getLowerSide().getPlayer();
        Player upper = game.getBoard().getUpperSide().getPlayer();
        Assert.assertEquals(lower, game.getCurrentPlayer());

        Move move = new Move(game.getUuid(), 0, 2);
        moveExecutor.applyMove(move);
        Assert.assertEquals(upper, game.getCurrentPlayer());
    }

    @Test
    public void testGameEnds() throws IllegalAccessException {
        game.getBoard().getUpperSide().getPit(0).setStones(0);
        game.getBoard().getUpperSide().getPit(1).setStones(0);
        game.getBoard().getUpperSide().getPit(2).setStones(0);
        game.getBoard().getUpperSide().getPit(3).setStones(0);
        game.getBoard().getUpperSide().getPit(4).setStones(0);
        game.setCurrentPlayer(game.getBoard().getUpperSide().getPlayer());
        /*
        Current game structure
             |6|0|0|0|0|0|
        ( 0 )              ( 0 )
             |6|6|6|6|6|6|
        will play index 5 for upperSide
        Target
             |0|0|0|0|0|0|
        ( 1 )              ( 41 )
             |0|0|0|0|0|0|
        * */

        Assert.assertFalse(game.isGameOver());

        Move move = new Move(game.getUuid(), 1, 5);
        moveExecutor.applyMove(move);

        Assert.assertTrue(game.isGameOver());
        Assert.assertEquals(1, game.getBoard().getUpperSide().getLargePit().getStones());
        Assert.assertEquals(41, game.getBoard().getLowerSide().getLargePit().getStones());

        IllegalAccessException thrown = assertThrows(
                IllegalAccessException.class,
                () -> moveExecutor.applyMove(move),
                "Exception should be thrown"
        );
        Assert.assertTrue(thrown.getMessage().contains("Game is already over!"));
    }

    @Test
    public void testWrongPlayerTurnMove() {
        Move move = new Move(game.getUuid(), 1, 5);
        IllegalAccessException thrown = assertThrows(
                IllegalAccessException.class,
                () -> moveExecutor.applyMove(move),
                "Exception should be thrown"
        );
        Assert.assertTrue(thrown.getMessage().contains("Wrong Player!"));
    }

    @Test
    public void testMoveEmptyPit() {
        game.getBoard().getLowerSide().getPit(0).setStones(0);
        Move move = new Move(game.getUuid(), 0, 0);
        IllegalAccessException thrown = assertThrows(
                IllegalAccessException.class,
                () -> moveExecutor.applyMove(move),
                "Exception should be thrown"
        );
        Assert.assertTrue(thrown.getMessage().contains("No stones for this pit!"));
    }
}
