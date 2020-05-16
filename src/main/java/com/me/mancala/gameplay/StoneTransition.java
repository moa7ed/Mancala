package com.me.mancala.gameplay;

import com.me.mancala.models.Pit;

public class StoneTransition {
    private Pit from;
    private Pit to;
    private int stones;

    public StoneTransition(Pit from, Pit to, int stones) {
        this.from = from;
        this.to = to;
        this.stones = stones;
    }

    private void applyTransition(int stones) {
        this.from.removeStones(stones);
        this.to.addStones(stones);
    }

    public Pit getFrom() {
        return from;
    }

    public Pit getTo() {
        return to;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }
}
