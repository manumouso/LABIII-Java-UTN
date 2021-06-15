package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpExchange;
import edu.utn.json.Constants;
import edu.utn.json.JsonController;
import edu.utn.manager.ServiceManager;

import javax.json.Json;

import javax.json.JsonObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Join extends HttpHandlers {

    public Join(ServiceManager serviceManager) {
        super(serviceManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(Constants.POST)) {

            String requestBody = JsonController.streamToString(exchange.getRequestBody());
            JsonObject object = JsonController.stringJsonToJsonObject(requestBody);

            int port = object.getInt("port");

            serviceManager.setExternalMessage(true);
            serviceManager.setRemoteIp(exchange.getRemoteAddress().getHostName());
            System.out.println(exchange.getRemoteAddress().getHostName());
            serviceManager.setRemotePort(port);
            System.out.println(port);

            JsonObject res;

            try {

                res = Json.createObjectBuilder()
                        .add("message","Connected to the server")
                        .build();
            }
            catch (Exception ex) {
                res = Json.createObjectBuilder()
                        .add("message", ex.getMessage())
                        .build();
            }

            exchange.getResponseHeaders().set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
            exchange.sendResponseHeaders(200, 0);

            try (BufferedOutputStream out = new BufferedOutputStream(exchange.getResponseBody())) {
                byte [] data = res.toString().getBytes();
                try (ByteArrayInputStream bis = new ByteArrayInputStream(data)) {
                    byte [] buffer = new byte [data.length];
                    int count ;
                    while ((count = bis.read(buffer)) != -1) {
                        out.write(buffer, 0, count);
                    }
                }
            }

        } else {
            new JsonController.NotImplementedHandler().handle(exchange);
        }
    }
}
