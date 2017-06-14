package com.domain.server.controller;

import com.google.gson.*;
import com.domain.server.database.Strings;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by schelde on 12/06/17.
 */
@Path("servers")
public class ServerResource {
    private List<String> stringList = Strings.getSingleton().getStringList();

    @Context
    private UriInfo context;

    public ServerResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomAddress() {
        System.out.println("Getting the strings" + stringList.toString());
        return Response
                .ok()
                .entity(getAllTexts())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveRandomText(@FormParam("text") String text) {
        System.out.println(text);
        stringList.add(text);
        return Response
                .ok()
                .entity(getAllTexts())
                .build();
    }

    private String getAllTexts() {
        JsonArray jsonArray = new JsonArray();
        for (String string : stringList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text", string);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

}
