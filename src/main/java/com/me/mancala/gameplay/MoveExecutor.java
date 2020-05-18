package com.me.mancala.gameplay;

import com.me.mancala.gameplay.models.Move;
import com.me.mancala.gameplay.models.StoneTransition;
import com.me.mancala.models.Game;
import com.me.mancala.models.LargePit;
import com.me.mancala.models.Pit;
import com.me.mancala.models.Player;
import com.me.mancala.models.Side;
import com.me.mancala.models.SmallPit;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MoveExecutor {
    @Nonnull
    private Game game;

    @Nonnull
    public MoveExecutor(@Nonnull Game game) {
        this.game = game;
    }

    @Nonnull
    public List<StoneTransition> applyMove(@Nonnull Move move) throws IllegalAccessException {
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

    @Nonnull
    private List<StoneTransition> distributeStones(@Nonnull Player player, @Nonnull Pit pit) {
        List<StoneTransition> stoneTransitions = new ArrayList<>();
        int stones = pit.getStones();
        Pit nextPit = pit;
        while (stones > 0) {
            nextPit = this.game.getBoard().nextPit(player, nextPit);
            stoneTransitions.add(new StoneTransition(pit, nextPit, 1));
            stones--;
        }
        return stoneTransitions;
    }

    private void appendLastBitSpecialCase(@Nonnull Player player, @Nonnull List<StoneTransition> stoneTransitions) {
        Pit lastPit = stoneTransitions.get(stoneTransitions.size() - 1).getTo();
        if (lastPit.getSide().getPlayer() == player && lastPit instanceof SmallPit && lastPit.getStones() == 1) {
            stoneTransitions.add(new StoneTransition(
                    lastPit,
                    lastPit.getSide().getLargePit(),
                    lastPit.getStones())
            );
            Pit oppositePit = this.game.getBoard().oppositePit(lastPit);
            stoneTransitions.add(new StoneTransition(
                    oppositePit,
                    lastPit.getSide().getLargePit(),
                    oppositePit.getStones())
            );
        }
    }

    private void updateCurrentPlayer(@Nonnull Player player, @Nonnull List<StoneTransition> stoneTransitions) {
        Pit lastPit = stoneTransitions.get(stoneTransitions.size() - 1).getTo();
        if (lastPit instanceof LargePit && lastPit.getSide().getPlayer() == player) {
            return; // player should play again
        }
        this.game.nextPlayerTurn();
    }

    private void checkIfGameOver(@Nonnull List<StoneTransition> stoneTransitions) {
        if (this.game.isGameOver()) {
            return;
        }
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
            if (lowerPlayerStones < upperPlayerStones) {
                this.game.setWinner(this.game.getBoard().getUpperSide().getPlayer());
            } else if (lowerPlayerStones > upperPlayerStones) {
                this.game.setWinner(this.game.getBoard().getLowerSide().getPlayer());
            }
        }
    }

    private void moveRestOfStonesToMancala(@Nonnull Side side, @Nonnull List<StoneTransition> stoneTransitions) {
        Pit mancalaPit = side.getLargePit();
        for (Pit pit : side.getPits()) {
            if (pit instanceof LargePit) {
                continue;
            }
            if (pit.getStones() > 0) {
                stoneTransitions.add(new StoneTransition(pit, mancalaPit, pit.getStones()));
            }
        }
    }

}
