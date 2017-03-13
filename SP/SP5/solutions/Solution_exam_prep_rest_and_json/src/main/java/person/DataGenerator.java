package person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Random;

/**
 * Created by scheldejonas on 10/03/17.
 */
public class DataGenerator {

    private static final DataGenerator singleton = new DataGenerator();
    private DataGenerator() {
    }

    public static DataGenerator getSingleton() {
        return singleton;
    }

    public String getData(int dataAmount, String arguments) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String[] argumentArray = arguments.split(",");
        System.out.println(argumentArray.toString());
        String[] firstNameArray = {"Jonas","Peter","Hans","Søren","Jones"};
        String[] lastNameArray = {"Schelde","Petersen","Hansen","Sørensen","Andersen"};
        String[] streetNameArray = {"Road 1","Road 2","Road 3","Road 4","Road 5"};
        String[] cityArray = {"Kolding", "Lyngby", "København", "Petersburg", "New York"};
        JsonArray names = new JsonArray();
        for (int d = 0; d < dataAmount; d++) {
            JsonObject person = new JsonObject();
            for (int i = 0; i < argumentArray.length; i++) {
                Random random = new Random();
                int randomNumber = random.nextInt(5);
                if (argumentArray[i].equals("fname")) {
                    person.addProperty("fname", firstNameArray[randomNumber]);
                }
                if (argumentArray[i].equals("lname")) {
                    person.addProperty("lname", lastNameArray[randomNumber]);
                }
                if (argumentArray[i].equals("street")) {
                    person.addProperty("street", streetNameArray[randomNumber]);
                }
                if (argumentArray[i].equals("city")) {
                    person.addProperty("city", cityArray[randomNumber]);
                }
            }
            names.add(person);
        }
        String jsonStr = gson.toJson(names); //The JSON string is ready
        System.out.println(jsonStr);
        return jsonStr;
    }
}
