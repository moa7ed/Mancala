package com.me.mancala.gameplay;

import com.me.mancala.models.Board;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import com.me.mancala.models.Side;

import javax.annotation.Nonnull;

public class GameInitializer {
    public static final int PITS_PER_SIDE = 6;
    public static final int STONES_PER_PIT = 6;

    @Nonnull
    public static Game initializeGame(@Nonnull Player lowerPlayer, @Nonnull Player upperPlayer) {
        Side lowerSide = new Side(STONES_PER_PIT, PITS_PER_SIDE, lowerPlayer);
        Side upperSide = new Side(STONES_PER_PIT, PITS_PER_SIDE, upperPlayer);
        Board board = new Board(lowerSide, upperSide, PITS_PER_SIDE);
        Game game = new Game(board);
        game.setCurrentPlayer(lowerPlayer);
        return game;
    }

}
