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

    public abstract void onSuccess(int statusCode, Map<String, List<String>> headers, String content);

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

    protected String readToString(HttpURLConnection connection){
        String message = null;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            message=response.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    protected void processResponse(HttpURLConnection connection) {
        try {
            // Response
            int responseCode = connection.getResponseCode();
            long contentLength = connection.getContentLength();
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();

            // Successful response codes will be in interval [200,300)
            if (responseCode >= 200 && responseCode < 300) {
                String responseContent = readToString(connection);
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