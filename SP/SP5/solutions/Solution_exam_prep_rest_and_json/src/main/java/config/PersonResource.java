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
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(personGenerator.getData(Integer.parseInt(addressAmount),arguments))
                .build();

    }

}
//    /**
//     * Retrieves representation of an instance of rest.PersonResource
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
////        ResponseBuilder builder = Response.ok(gson.toJson(new Person(1, "Pedersen", 32)));
////        builder.cacheControl(cc);
////        return builder.build();
//            JsonObject job = new JsonObject();
//            job.addProperty("name", "Pedersen");
//        //String jsonstr = gson.toJson(new Person(1, "Pedersen", 32));
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
////        ResponseBuilder builder = Response.ok(gson.toJson(new Person(1, "Pedersen", 32)));
////        builder.cacheControl(cc);
////        return builder.build();
//        Person p = new Person(1, "Pedersen", 32);
////        Person p = new Person();
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
//        Person person = gson.fromJson(content, Person.class);
//        person.setAge(person.getAge() + 1);
//        return gson.toJson(person);
//        //return content;
//    }
