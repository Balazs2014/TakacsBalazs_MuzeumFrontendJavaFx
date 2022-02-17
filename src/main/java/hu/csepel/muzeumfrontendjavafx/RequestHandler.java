package hu.csepel.muzeumfrontendjavafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandler {
    private RequestHandler() {

    }

    public static Response get(String url) throws IOException {
        HttpURLConnection conn = setConnection(url);

        return getResponse(conn);
    }

    private static Response getResponse(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();
        InputStream is;
        if(responseCode < 400) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String sor = reader.readLine();
        while (sor != null) {
            builder.append(sor);
            sor = reader.readLine();
        }
        reader.close();
        is.close();

        return new Response(responseCode, builder.toString());
    }

    private static HttpURLConnection setConnection(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        return conn;
    }
}
