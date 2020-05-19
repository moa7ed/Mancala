package com.me.mancala.resources;

import com.codahale.metrics.annotation.Timed;
import com.me.mancala.gameplay.GameDb;
import com.me.mancala.gameplay.MoveExecutor;
import com.me.mancala.gameplay.models.Move;
import com.me.mancala.models.Game;
import com.me.mancala.resources.requests.MoveRequest;

import javax.annotation.Nonnull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/moves")
@Produces(MediaType.APPLICATION_JSON)
public class MoveResource {

    private final GameDb gameDb = GameDb.getInstance();

    @POST
    @Timed
    @Path("/apply_move")
    @Nonnull
    public Response applyMove(MoveRequest moveRequest) {
        Game game = gameDb.fetch(moveRequest.getGameId());
        MoveExecutor moveExecutor = new MoveExecutor(game);
        try {
            moveExecutor.applyMove(new Move(moveRequest.getSideIndex(), moveRequest.getPitIndex()));
        } catch (IllegalAccessException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }
}
