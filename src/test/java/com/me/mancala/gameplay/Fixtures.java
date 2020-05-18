package com.me.mancala.gameplay;

import com.github.javafaker.Faker;
import com.me.mancala.models.Board;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;

public class Fixtures {
    private final static Faker faker = new Faker();

    public static Game testGame() {
        Player player1 = testPlayer();
        Player player2 = testPlayer();
        return GameInitializer.initializeGame(player1, player2);
    }

    public static Player testPlayer() {
        return new Player(faker.name().firstName());
    }

    public static Board testBoard() {
        return testGame().getBoard();
    }
}
