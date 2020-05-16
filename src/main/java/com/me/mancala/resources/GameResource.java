package com.me.mancala.resources;
import com.codahale.metrics.annotation.Timed;
import com.me.mancala.gameplay.GameInitializer;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import com.me.mancala.resources.requests.CreateGameRequest;
import com.me.mancala.views.GameView;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/games")
@Produces(MediaType.TEXT_HTML)
public class GameResource {

    @POST
    @Timed
    public GameView newGame(@Nonnull @Valid CreateGameRequest createGameRequest) {
        Game game = GameInitializer.initializeGame(
                createGameRequest.getUpperPlayer(),
                createGameRequest.getLowerPlayer()
        );
        return new GameView(game);
    }

    @GET
    @Timed
    @Path("/{game_id}")
    public GameView getGame(@PathParam("game_id") int gameId) {
        Game game = GameInitializer.initializeGame(
                new Player("player 1"),
                new Player("player 2")
        );
        return new GameView(game);
    }
}
