package com.me.mancala.models;

import javax.annotation.Nonnull;

public class Side {
    @Nonnull
    private final Pit[] playablePits;
    @Nonnull
    private final Pit largePit;
    @Nonnull
    private final Player player;

    public Side(int pitsPerSide, int stonesPerPit, @Nonnull Player player) {
        this.playablePits = new SmallPit[pitsPerSide];
        for (int i = 0; i < pitsPerSide; i++) {
            this.playablePits[i] = new SmallPit(stonesPerPit, this, i);
        }
        this.largePit = new LargePit(this, pitsPerSide);
        this.player = player;
    }

    public Pit[] getPits() {
        return this.playablePits;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    public Pit getPit(int pitIndex) {
        return this.playablePits[pitIndex];
    }

    public Pit getLargePit() {
        return this.largePit;
    }

    public boolean isEmpty() {
        for (Pit pit : this.playablePits) {
            if (!pit.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
