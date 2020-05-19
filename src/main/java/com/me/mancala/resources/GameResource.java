package com.me.mancala.resources;

import com.codahale.metrics.annotation.Timed;
import com.me.mancala.gameplay.GameDb;
import com.me.mancala.gameplay.GameInitializer;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import com.me.mancala.resources.requests.CreateGameRequest;
import com.me.mancala.resources.responses.GameResponse;
import com.me.mancala.views.GameListView;
import com.me.mancala.views.GameView;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/games")
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @Nonnull
    private final GameDb gameDb = GameDb.getInstance();

    @GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    @Nonnull
    public GameListView listGames() {
        return new GameListView(
                gameDb.listAllGames()
                        .stream()
                        .map(uuid -> uuid.toString())
                        .collect(Collectors.toList())
        );
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Nonnull
    public GameResponse newGame(@Nonnull @Valid CreateGameRequest createGameRequest) {
        Game game = GameInitializer.initializeGame(
                new Player(createGameRequest.getLowerPlayer()),
                new Player(createGameRequest.getUpperPlayer())
        );
        gameDb.insert(game);
        return new GameResponse(game.getUuid().toString());
    }

    @GET
    @Timed
    @Path("/{game_id}")
    @Produces(MediaType.TEXT_HTML)
    @Nonnull
    public GameView getGame(@PathParam("game_id") UUID gameId) {
        Game game = gameDb.fetch(gameId);
        return new GameView(game);
    }
}
