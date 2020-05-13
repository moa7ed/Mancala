package com.me.mancala.models;

public class Side {
    private final SideType sideType;
    private final Pit[] pits;
    private final LargePit largePit;
    private final Player player;

    public Side(SideType sideType, Pit[] pits, Player player) {
        this.sideType = sideType;
        this.pits = pits;
        this.largePit = new LargePit();
        this.player = player;
    }

    public Pit[] getPits() {
        return pits;
    }

    public Player getPlayer() {
        return player;
    }

    public Pit getPit(int pitIndex) {
        return this.pits[pitIndex];
    }

    public boolean isEmpty() {
        for (Pit pit : this.pits) {
            if (!pit.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public enum SideType {
        UPPER,
        LOWER;
    }
}
