package edu.utn.connection.client;


import edu.utn.json.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Client {

    private int connectionTimeout = 20000; // in milliseconds
    private int dataRetrievalTimeout = 20000; // in milliseconds
    private boolean followRedirects = false; // automatically follow HTTP redirects

    public Client() {

    }

    protected void request(String url, ResponseHandler handler,String jsonInput) {

        HttpURLConnection urlConnection = null;


        try {
            URL resourceUrl = new URL(url);
            urlConnection = (HttpURLConnection) resourceUrl.openConnection();

            // Settings
            urlConnection.setConnectTimeout(connectionTimeout);
            urlConnection.setReadTimeout(dataRetrievalTimeout);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(followRedirects);
            urlConnection.setRequestMethod(Constants.POST);
            urlConnection.setDoInput(true);

            // Header
            urlConnection.setRequestProperty(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);


            handler.onStart(urlConnection);

            // Request Body
            // POST JSON
            urlConnection.setDoOutput(true);

            String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
            try(OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                urlConnection.setFixedLengthStreamingMode(input.length);
                os.write(input);
            }

            // Process the response in the handler because it can be done in different ways
            handler.processResponse(urlConnection);
            // Request finished
            handler.onFinish(urlConnection);

        } catch (IOException e) {
            handler.onFailure(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

   public void post(String url, ResponseHandler handler,String input) {
        request(url, handler,input);
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getDataRetrievalTimeout() {
        return dataRetrievalTimeout;
    }

    public void setDataRetrievalTimeout(int dataRetrievalTimeout) {
        this.dataRetrievalTimeout = dataRetrievalTimeout;
    }

}