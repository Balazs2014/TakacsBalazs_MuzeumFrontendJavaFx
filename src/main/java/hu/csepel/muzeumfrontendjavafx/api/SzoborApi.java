package hu.csepel.muzeumfrontendjavafx.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.csepel.muzeumfrontendjavafx.Controller;
import hu.csepel.muzeumfrontendjavafx.classes.Szobor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SzoborApi extends Controller {
    private static final String API_URL = "http://127.0.0.1:8000/api/statues";
    private static Gson jsonConverter = new Gson();

    public static List<Szobor> get() throws IOException {
        String json = Api.get(API_URL);
        Type type = new TypeToken<List<Szobor>>() {
        }.getType();
        return jsonConverter.fromJson(json, type);
    }

    public static Szobor post(Szobor uj) throws IOException {
        String ujJson = jsonConverter.toJson(uj);
        String json = Api.post(API_URL, ujJson);
        return jsonConverter.fromJson(json, Szobor.class);
    }

    public static boolean delete(int id) throws IOException {
        return Api.delete(API_URL, id).getResponseCode() == 204;
    }

    public static Szobor put(Szobor modosit, int id) throws IOException {
        String modositJson = jsonConverter.toJson(modosit);
        String json = Api.put(API_URL, modositJson, id);
        return jsonConverter.fromJson(json, Szobor.class);
    }
}
