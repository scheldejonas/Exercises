/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package country;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Schelde Jonas
 */
@Path("countries")
public class CountryResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CountryResource
     */
    public CountryResource() {
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountriesByArguments(@QueryParam("arguments") String columns) {
        System.out.println("Testing: I was inside get all countries by arguments");
        String countriesJsonString = gson.toJson(CountryDao.getSingleton().findAll());
        System.out.println("Testing countries: \n" + countriesJsonString);
        return Response
                .status(200)
                .entity(countriesJsonString)
                .build();
    }
}

//    @GET
//    @Path("/{addressAmount}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getRandomAddress(@PathParam("addressAmount") String addressAmount, @QueryParam("arguments") String arguments) {
//        System.out.println("Testing amount recieved through pathparam: " + addressAmount);
//        System.out.println("Testing arguments recieved through p athparam: " + arguments);
//        return Response
//                .status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .header("Access-Control-Max-Age", "1209600")
//                .entity(personGenerator.getData(Integer.parseInt(addressAmount),arguments))
//                .build();
//
//    }
//    /**
//     * Retrieves representation of an instance of rest.CountryResource
//     *
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getHtml() {
//        //TODO return proper representation object
//        return "{\"name\":\"Henrik\", \"id\":1}";
//    }
//
//    @GET
//    @Path("/cache")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getWithCaching() {
//        CacheControl cc = new CacheControl();
//        cc.setMaxAge(86400);
//        cc.setPrivate(true);
////
////        ResponseBuilder builder = Response.ok(gson.toJson(new Country(1, "Pedersen", 32)));
////        builder.cacheControl(cc);
////        return builder.build();
//            JsonObject job = new JsonObject();
//            job.addProperty("name", "Pedersen");
//        //String jsonstr = gson.toJson(new Country(1, "Pedersen", 32));
//        return Response.ok().entity(gson.toJson(job)).cacheControl(cc).build();
//
//    }
//    @GET
//    @Path("/test")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String testPerson() {
//        CacheControl cc = new CacheControl();
//        cc.setMaxAge(86400);
//        cc.setPrivate(true);
////
////        ResponseBuilder builder = Response.ok(gson.toJson(new Country(1, "Pedersen", 32)));
////        builder.cacheControl(cc);
////        return builder.build();
//        Country p = new Country(1, "Pedersen", 32);
////        Country p = new Country();
////        p.setName("Hansen");
////        p.setAge(32);
//        String jsonstr = gson.toJson(p);
//        return jsonstr;
////        return Response.ok().entity(gson.toJson(jsonstr)).cacheControl(cc).build();
//
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String postPerson(String content) {
//        Country person = gson.fromJson(content, Country.class);
//        person.setAge(person.getAge() + 1);
//        return gson.toJson(person);
//        //return content;
//    }
