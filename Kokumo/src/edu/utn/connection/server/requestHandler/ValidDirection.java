package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpExchange;
import edu.utn.json.Constants;
import edu.utn.json.JsonController;
import edu.utn.manager.RuleManager;
import edu.utn.model.ninja.NinjaPosition;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ValidDirection extends HttpHandlers{
    public ValidDirection(RuleManager ruleManager) {
        super(ruleManager);
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(Constants.POST)) {
            String requestBody = JsonController.streamToString(exchange.getRequestBody());
            JsonObject object = JsonController.stringJsonToJsonObject(requestBody);

            int nextI = object.getInt("nextI");
            int nextJ = object.getInt("nextJ");
            JsonArray pos1 = object.getJsonArray("pos1");
            JsonArray pos2 = object.getJsonArray("pos2");
            JsonArray pos3 = object.getJsonArray("pos3");

            NinjaPosition ninjaPos1 = new NinjaPosition(pos1.getInt(0),pos1.getInt(1));
            NinjaPosition ninjaPos2 = new NinjaPosition(pos2.getInt(0),pos2.getInt(1));
            NinjaPosition ninjaPos3 = new NinjaPosition(pos3.getInt(0),pos3.getInt(1));

            String message = ruleManager.validDirectionClient(nextI,nextJ,ninjaPos1,ninjaPos2,ninjaPos3);

            JsonObject res;

            try {
                res= Json.createObjectBuilder()
                        .add("message",message)
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
