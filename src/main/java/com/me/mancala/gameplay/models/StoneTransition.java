package com.me.mancala.gameplay.models;

import com.me.mancala.models.Pit;

import javax.annotation.Nonnull;

public class StoneTransition {
    @Nonnull
    private Pit from;
    @Nonnull
    private Pit to;
    private int stones;

    public StoneTransition(@Nonnull Pit from, @Nonnull Pit to, int stones) {
        this.from = from;
        this.to = to;
        this.stones = stones;
        applyTransition(this.stones);
    }

    private void applyTransition(int stones) {
        this.from.removeStones(stones);
        this.to.addStones(stones);
    }

    @Nonnull
    public Pit getFrom() {
        return from;
    }

    @Nonnull
    public Pit getTo() {
        return to;
    }
}
