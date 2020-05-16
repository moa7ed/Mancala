package com.me.mancala.gameplay;

import com.me.mancala.models.*;

public class GameInitializer {
    public static final int PITS_PER_SIDE = 6;

    public static Game initializeGame(Player upperPlayer, Player lowerPlayer) {
        Side upperSide = new Side(PITS_PER_SIDE, upperPlayer);
        Side lowerSide = new Side(PITS_PER_SIDE, lowerPlayer);
        Board board = new Board(upperSide, lowerSide);
        return new Game(board);
    }

}
