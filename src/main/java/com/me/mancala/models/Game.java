package com.me.mancala.models;

import java.util.UUID;

public class Game {
    private final UUID uuid;
    private final Board board;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;

    public Game(Board board) {
        this.uuid = UUID.randomUUID();
        this.winner = new Player("7amada");
        this.board = board;
        this.currentPlayer = this.board.getLowerSide().getPlayer();
        this.gameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public UUID getUuid() {
        return uuid;
    }

}
