/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("countries")
public class CountryResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public CountryResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryByCode(@QueryParam("countryId") String countryId) {
        String countryJson = "";
        countryJson = getResponseFromCountryService(countryId);
        return Response
                .ok()
                .entity(countryJson)
                .build();
    }

    private String getResponseFromCountryService(String id) {
        System.out.println("...Found http in url.");
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String url = "http://restcountries.eu/rest/v1/alpha?codes=" + id;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            System.out.println("...Tried to open a http connection");
            e.printStackTrace();
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        } catch (IOException e) {
            System.out.printf("...Tried to get input stream from http connection");
            e.printStackTrace();
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (bufferedReader != null) {
                while (bufferedReader.ready()) {
                    try {
                        stringBuilder.append(bufferedReader.readLine());
                    } catch (IOException e) {
                        System.out.println("...Tried to readline from input stream as URLConnection");
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("...Tried to loop bufferedReader, but failed.");
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
