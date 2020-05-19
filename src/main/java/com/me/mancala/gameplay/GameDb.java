package com.me.mancala.gameplay;

import com.me.mancala.models.Game;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

// Just a in memory cache for simplicity
public class GameDb {
    @Nonnull
    private static GameDb gameDb = new GameDb();
    @Nonnull
    private Map<UUID, Game> gameCache = new HashMap<>();

    @Nonnull
    public static GameDb getInstance() {
        return gameDb;
    }

    private GameDb() {
    }

    public synchronized void insert(@Nonnull Game game) throws DuplicateKeyException {
        if (gameCache.containsKey(game.getUuid())) {
            throw new IllegalArgumentException(String.format("Game %s already exist", game.getUuid()));
        }
        gameCache.put(game.getUuid(), game);
    }

    @Nonnull
    public synchronized Game fetch(@Nonnull UUID gameId) {
        if (!gameCache.containsKey(gameId)) {
            throw new IllegalArgumentException(String.format("Game %s is not exist", gameId));
        }
        return gameCache.get(gameId);
    }

    @Nonnull
    public synchronized List<UUID> listAllGames() {
        return gameCache.values().stream().map(Game::getUuid).collect(Collectors.toList());
    }
}
