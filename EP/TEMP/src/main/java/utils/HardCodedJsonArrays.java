package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by scheldejonas on 16/04/2017.
 */
public class HardCodedJsonArrays {

    public static JsonArray getAllUsers() {
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < 3; i++) {
            JsonObject jsonObject = new JsonObject();
            for (int ii = 0; ii < 2; ii++) {
                String property = "";
                String value = "";
                if (i == 0 && ii == 0) {
                    property = "name";
                    value = "Jan";
                }
                if (i == 0 && ii == 1) {
                    property = "mail";
                    value = "j@a.dk";
                }
                if (i == 1 && ii == 0) {
                    property = "name";
                    value = "Ann";
                }
                if (i == 1 && ii == 1) {
                    property = "mail";
                    value = "a@a.dk";
                }
                if (i == 2 && ii == 0) {
                    property = "name";
                    value = "ib";
                }
                if (i == 2 && ii == 1) {
                    property = "mail";
                    value = "i@a.dk";
                }
                jsonObject.addProperty(property,value);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static JsonArray getFootballClubs() {
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < 2; i++) {
            JsonObject jsonObject = new JsonObject();
            for (int ii = 0; ii < 2; ii++) {
                String property = "";
                String value = "";
                if (i == 0 && ii == 0) {
                    property = "name";
                    value = "Liverpool";
                }
                if (i == 0 && ii == 1) {
                    property = "url";
                    value = "http://www.liverpoolfc.com";
                }
                if (i == 1 && ii == 0) {
                    property = "name";
                    value = "Manchester United";
                }
                if (i == 1 && ii == 1) {
                    property = "url";
                    value = "http://www.manutd.com";
                }
                jsonObject.addProperty(property,value);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
