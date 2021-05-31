package edu.utn.json;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.utn.model.ninja.Ninja;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.json.*;

public class JsonController {

    public static String streamToString(InputStream inputStream) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return  out.toString();
    }

    public static JsonObject stringJsonToJsonObject(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    public static class NotImplementedHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(501, -1);
            exchange.close();
        }
    }
    //lo voy a redefinir cuando cree bien el modelo de la clase que va a viajar json
    //lista de ninjas no es representativo del json, ADAPATAR A que me genere los JSON ARRAY:
    //: attack pos, attack points y msgs
    public static JsonArray mapListToJsonArray(List<Ninja> ninjas) {

        List<JsonObject> jsonList = ninjas.stream().map(n -> n.toJsonObject()).collect(Collectors.toList());

        JsonArrayBuilder builder = Json.createArrayBuilder();
        jsonList.forEach(n ->  builder.add(n));
        return builder.build();
    }

    public static Map<String, String> queryParamsToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
