/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.domain.restapi.domain.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

/**
 *
 * @author schelde
 */
public class EntitySerializer {
    private static final EntitySerializer singleton = new EntitySerializer();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private EntitySerializer() {
    }
    
    public static EntitySerializer getSingleton() {
        return singleton;
    }
    public JsonObject serializeCountry(Country country) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", country.getCode());
        jsonObject.addProperty("name", country.getName());
        jsonObject.addProperty("continent", country.getContinent());
        jsonObject.addProperty("capitalName", country.getCapital().getName());
        return jsonObject;
    }
    
    public JsonArray serializeCountryList(List<Country> countryList) {
        JsonArray jsonArray = new JsonArray();
        for (Country country : countryList) {
            jsonArray.add(serializeCountry(country));
        }
        return jsonArray;
    }
}
