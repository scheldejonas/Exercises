package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import utils.HardCodedJsonArrays;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by scheldejonas on 15/04/2017.
 */
@Path("footballclubs")
@RolesAllowed("User")
public class FootballClubs {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwoHardcodedClubs() {
        JsonArray jsonArray = HardCodedJsonArrays.getFootballClubs();
        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(jsonArray))
                .build();
    }
}
