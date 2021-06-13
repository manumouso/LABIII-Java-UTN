package edu.utn.connection.client;


import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


public abstract class HttpResponseHandler {

    protected static int BUFFER_SIZE = 1024 * 8; // Size of the buffer when reading data from output stream


    public void onStart(HttpURLConnection httpURLConnection) {
        // Do  nothing by default
    }


    public void onFinish(HttpURLConnection httpURLConnection) {
        // Do nothing by default
    }

    public abstract void onSuccess(int statusCode, Map<String, List<String>> headers, byte[] content);

    public abstract void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content);

    public abstract void onFailure(Throwable throwable);

    public void onProgressChanged(long bytesReceived, long totalBytes) {
        // Default, do nothing.
    }

    protected byte[] readFrom(InputStream inputStream, long length) throws IOException {
        if (inputStream == null) return new byte[0];

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, bytesRead);
            onProgressChanged(bytesRead, length);
        }
        os.flush();
        os.close();
        return os.toByteArray();
    }

    //puedo devolver el String y despues parsearlo, pero creo que imprimiendo el mensaje ya estoy
    protected void readToString(HttpURLConnection connection){
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void processResponse(HttpURLConnection connection) {
        try {
            // Response
            int responseCode = connection.getResponseCode();
            long contentLength = connection.getContentLength();
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();

            // Successful response codes will be in interval [200,300)
            if (responseCode >= 200 && responseCode < 300) {
                byte[] responseContent = readFrom(connection.getInputStream(), contentLength);//or print the json as if with readToString()
                onSuccess(responseCode, responseHeaders, responseContent);
            } else {
                byte[] responseContent = readFrom(connection.getErrorStream(), contentLength);
                onFailure(responseCode, responseHeaders, responseContent);
            }
        } catch (IOException e) {
            onFailure(e);
        }
    }
}