package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.utn.entity.ninja.Ninja;
import edu.utn.entity.ninja.NinjaCommander;
import edu.utn.entity.ninja.NinjaPosition;
import edu.utn.json.Constants;
import edu.utn.json.JsonController;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Attack implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Me conecte joya");
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(Constants.POST)) {
            String requestBody = JsonController.streamToString(exchange.getRequestBody());
            JsonObject object = JsonController.stringJsonToJsonObject(requestBody);

            String position = object.getString("position");
            String attackPoints = object.getString("attackPoints");

            System.out.println(position);
            System.out.println(attackPoints);

            JsonObject res;

            //hardcode ninja
            Ninja ninja = new NinjaCommander("comandante",50,10,new NinjaPosition(1,1));
            try {

                res = ninja.toJsonObject();
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
