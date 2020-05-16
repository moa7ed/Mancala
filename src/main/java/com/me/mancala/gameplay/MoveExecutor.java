package com.me.mancala.gameplay;

import com.me.mancala.models.*;

import java.util.ArrayList;
import java.util.List;

public class MoveExecutor {
    private Game game;

    public MoveExecutor(Game game) {
        this.game = game;
    }

    public List<StoneTransition> applyMove(Move move) throws IllegalAccessException {
        if (this.game.isGameOver()) {
            throw new IllegalAccessException("Game is already over!");
        }
        Side side = game.getBoard().getSide(move.getSideIndex());
        Player player = side.getPlayer();
        if (player != game.getCurrentPlayer()) {
            throw new IllegalAccessException("Wrong Player!");
        }
        Pit pit = side.getPit(move.getPitIndex());
        if (pit.getStones() == 0) {
            throw new IllegalAccessException("No stones for this pit!");
        }
        List<StoneTransition> stoneTransitions = distributeStones(player, pit);
        appendLastBitSpecialCase(player, stoneTransitions);
        updateCurrentPlayer(player, stoneTransitions);
        checkIfGameOver(stoneTransitions);
        return stoneTransitions;
    }

    private void checkIfGameOver(List<StoneTransition> stoneTransitions) {
        boolean gameOver = false;
        if (this.game.getBoard().getLowerSide().isEmpty()) {
            moveRestOfStonesToMancala(this.game.getBoard().getUpperSide(), stoneTransitions);
            gameOver = true;
        } else if (this.game.getBoard().getUpperSide().isEmpty()) {
            moveRestOfStonesToMancala(this.game.getBoard().getLowerSide(), stoneTransitions);
            gameOver = true;
        }
        if (gameOver) {
            int lowerPlayerStones = this.game.getBoard().getLowerSide().getLargePit().getStones();
            int upperPlayerStones = this.game.getBoard().getUpperSide().getLargePit().getStones();
            this.game.setGameOver(true);
            Player winner;
            if (lowerPlayerStones < upperPlayerStones) {
                winner = this.game.getBoard().getUpperSide().getPlayer();
            } else if (lowerPlayerStones > upperPlayerStones) {
                winner = this.game.getBoard().getLowerSide().getPlayer();
            } else {
                winner = null; // tie
            }
            this.game.setWinner(winner);
        }
    }

    private void moveRestOfStonesToMancala(Side lowerSide, List<StoneTransition> stoneTransitions) {
        Pit mancalaPit = lowerSide.getLargePit();
        for (Pit pit : lowerSide.getPits()) {
            if (pit instanceof LargePit) {
                continue;
            }
            if (pit.getStones() > 0) {
                stoneTransitions.add(new StoneTransition(pit, mancalaPit, pit.getStones()));
            }
        }
    }


    private void updateCurrentPlayer(Player player, List<StoneTransition> stoneTransitions) {
        Pit lastPit = stoneTransitions.get(stoneTransitions.size() - 1).getTo();
        if (lastPit instanceof LargePit && lastPit.getSide().getPlayer() == player) {
            return; // player should play again
        }
        Side oppositeSide = this.game.getBoard().oppositeSide(lastPit.getSide());
        this.game.setCurrentPlayer(oppositeSide.getPlayer());
    }

    private void appendLastBitSpecialCase(Player player, List<StoneTransition> stoneTransitions) {
        Pit lastPit = stoneTransitions.get(stoneTransitions.size() - 1).getTo();
        if (lastPit instanceof SmallPit && lastPit.getStones() == 1) {
            stoneTransitions.add(new StoneTransition(
                    lastPit,
                    lastPit.getSide().getLargePit(),
                    lastPit.getStones())
            );
            Pit oppositePit = this.game.getBoard().oppositePit(lastPit);
            stoneTransitions.add(new StoneTransition(
                    oppositePit,
                    lastPit.getSide().getLargePit(),
                    lastPit.getStones())
            );
        }
    }

    private List<StoneTransition> distributeStones(Player player, Pit pit) {
        List<StoneTransition> stoneTransitions = new ArrayList<>();
        int stones = pit.getStones();
        while (stones > 0) {
            stoneTransitions.add(new StoneTransition(pit, nextPit(player, pit), 1));
        }
        return stoneTransitions;
    }

    private Pit nextPit(Player player, Pit pit) {
        Pit toPit = pit;
        if (toPit instanceof LargePit) {
            toPit = this.game.getBoard().oppositeSide(toPit.getSide()).getPit(0);
        } else {
            toPit = toPit.getSide().getPit(toPit.getIndex() + 1);
        }
        // other player Mancala (skip)
        if (toPit instanceof LargePit && toPit.getSide().getPlayer() != player) {
            return nextPit(player, toPit);
        }
        return toPit;
    }

}
