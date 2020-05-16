package com.me.mancala.resources;

import com.codahale.metrics.annotation.Timed;
import com.me.mancala.gameplay.GameDb;
import com.me.mancala.gameplay.GameInitializer;
import com.me.mancala.gameplay.Move;
import com.me.mancala.gameplay.MoveExecutor;
import com.me.mancala.models.Game;
import com.me.mancala.models.Player;
import com.me.mancala.resources.requests.MoveRequest;
import com.me.mancala.views.GameView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/games/{game_id}")
@Produces(MediaType.APPLICATION_JSON)
public class MoveResource {
    @POST
    @Timed
    @Path("/moves")
    public GameView applyMove(MoveRequest moveRequest) {
        GameDb gameDb = new GameDb();
        Game game = gameDb.fetch(moveRequest.getGameId());
        return null;
    }
}
