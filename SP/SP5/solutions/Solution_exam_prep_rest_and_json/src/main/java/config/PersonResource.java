/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import person.DataGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * REST Web Service
 *
 * @author Schelde Jonas
 */
@Path("addresses")
public class PersonResource {
    private DataGenerator personGenerator = DataGenerator.getSingleton();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    @GET
    @Path("/{addressAmount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomAddress(@PathParam("addressAmount") String addressAmount, @QueryParam("arguments") String arguments) {
        System.out.println("Testing amount recieved through pathparam: " + addressAmount);
        System.out.println("Testing arguments recieved through p athparam: " + arguments);
        return Response
                .status(200)
                .entity(personGenerator.getData(Integer.parseInt(addressAmount),arguments))
                .build();

    }

}