package com.me.mancala.models;

import javax.annotation.Nonnull;

public class Side {
    @Nonnull
    private final Pit[] pits;
    @Nonnull
    private final Player player;

    public Side(int pitsPerSide, @Nonnull Player player) {
        this.pits = new Pit[pitsPerSide + 1]; // 1 more for the largePit
        for (int i = 0; i < pitsPerSide; i++) {
            this.pits[i] = new SmallPit(this, i);
        }
        this.pits[pitsPerSide] = new LargePit(this, pitsPerSide);
        this.player = player;
    }

    public Pit[] getPits() {
        return pits;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    public Pit getPit(int pitIndex) {
        return this.pits[pitIndex];
    }

    public Pit getLargePit() {
        return this.pits[pits.length - 1];
    }

    public boolean isEmpty() {
        for (Pit pit : this.pits) {
            if (pit instanceof LargePit) {
                continue;
            }
            if (!pit.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
