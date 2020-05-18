package com.me.mancala.resources;

import com.codahale.metrics.annotation.Timed;
import com.me.mancala.resources.requests.MoveRequest;
import com.me.mancala.views.GameView;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/games/{game_id}")
@Produces(MediaType.APPLICATION_JSON)
public class MoveResource {
    @POST
    @Timed
    @Path("/moves")
    public GameView applyMove(MoveRequest moveRequest) {
        //GameDb gameDb = new GameDb();
        //Game game = gameDb.fetch(moveRequest.getGameId());
        return null;
    }
}
