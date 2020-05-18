package com.me.mancala.models;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class Game {
    @Nonnull
    private final UUID uuid;
    @Nonnull
    private final Board board;
    @Nullable
    private Player currentPlayer;
    @Nullable
    private Player winner;
    private boolean gameOver;

    public Game(@Nonnull Board board) {
        this.uuid = UUID.randomUUID();
        this.board = board;
        this.gameOver = false;
    }

    @Nonnull
    public Board getBoard() {
        return board;
    }

    @Nullable
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(@Nonnull Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Nullable
    public Player getWinner() {
        return winner;
    }

    public void setWinner(@Nonnull Player winner) {
        this.winner = winner;
    }

    public void nextPlayerTurn() {
        Player lowerPlayer = this.board.getLowerSide().getPlayer();
        Player upperPlayer = this.board.getUpperSide().getPlayer();
        this.currentPlayer = (currentPlayer == lowerPlayer) ? upperPlayer : lowerPlayer;
    }

    @Nonnull
    public UUID getUuid() {
        return uuid;
    }

}
