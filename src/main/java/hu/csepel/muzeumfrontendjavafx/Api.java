package hu.csepel.muzeumfrontendjavafx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.csepel.muzeumfrontendjavafx.classes.Festmeny;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Api {
    private static final String BASE_URL = "http://127.0.0.1:8000";
    private static final String FESTMENY_URL = BASE_URL + "/api/paintings";

    public static List<Festmeny> getFestmenyek() throws IOException {
        Response response = RequestHandler.get(FESTMENY_URL);
        String json = response.getContent();
        Gson jsonConverter = new Gson();
        if (response.getResponseCode() > 400) {
            String msg = jsonConverter.fromJson(json, ApiError.class).getMessage();
            throw new IOException(msg);
        }

        Type type = new TypeToken<List<Festmeny>>() {
        }.getType();

        return jsonConverter.fromJson(json, type);
    }

    public static Festmeny postFestmeny(Festmeny uj) throws IOException {
        Gson jsonConverter = new Gson();
        String ujJson = jsonConverter.toJson(uj);
        Response response = RequestHandler.post(FESTMENY_URL, ujJson);
        String json = response.getContent();
        if (response.getResponseCode() > 400) {
            String msg = jsonConverter.fromJson(json, ApiError.class).getMessage();
            throw new IOException(msg);
        }

        return jsonConverter.fromJson(json, Festmeny.class);
    }
}
