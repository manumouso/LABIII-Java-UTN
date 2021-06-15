package edu.utn.connection.client;


import edu.utn.json.Constants;
import edu.utn.json.JsonController;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public abstract class HttpClient {

    public HttpClient() {

    }

    protected String request(String url, String json) {

        HttpURLConnection urlConnection = null;

        try {
            URL resourceUrl = new URL(url);
            urlConnection = (HttpURLConnection) resourceUrl.openConnection();

            urlConnection.setRequestMethod(Constants.POST);
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
            urlConnection.setRequestProperty("Accept", "application/json");


            urlConnection.setDoOutput(true);

            try(OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input,0, input.length);
            }

            String response = JsonController.streamToString(urlConnection.getInputStream());
            JsonObject object = JsonController.stringJsonToJsonObject(response.toString());
            return object.getString("message");


        } catch (IOException e) {

            return "IO Exception: "+e.getMessage();
        }catch (Exception e){
            return "Exception: "+ e.getMessage();
        }
    }

    public String post(String url, String json) {
        return request(url,json);
    }



}