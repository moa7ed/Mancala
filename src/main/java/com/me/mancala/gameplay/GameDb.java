package com.me.mancala.gameplay;

import com.me.mancala.models.Game;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Just a in memory cache for simplicity
public class GameDb {
    private Map<UUID, Game> gameCache = new HashMap<>();

    public synchronized void insert(Game game) throws DuplicateKeyException {
        if (gameCache.containsKey(game.getUuid())) {
            throw new IllegalArgumentException(String.format("Game %s already exist", game.getUuid()));
        }
        gameCache.put(game.getUuid(), game);
    }

    public synchronized Game fetch(UUID gameId) {
        if (!gameCache.containsKey(gameId)) {
            throw new IllegalArgumentException(String.format("Game %s is not exist", gameId));
        }
        return gameCache.get(gameId);
    }
}
