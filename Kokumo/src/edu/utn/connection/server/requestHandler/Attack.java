package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpExchange;
import edu.utn.manager.ServiceManager;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaCommander;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.json.Constants;
import edu.utn.json.JsonController;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;


public class Attack extends Handlers{

    public Attack(ServiceManager service) {
        super(service);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Me conecte joya");
        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(Constants.POST)) {
            String requestBody = JsonController.streamToString(exchange.getRequestBody());
            JsonObject object = JsonController.stringJsonToJsonObject(requestBody);

            //ACA LEVANTO LA FUTURA CLASE CON LO QUE TIENE EL JSON
            JsonArray position = object.getJsonArray("position");//.getString("position");
            String attackPoints = object.getString("attackPoints");

            //ACA LLAMO a funcion ATACAR que le paso el objeto que cree arriba
            System.out.println(position.get(0));
            System.out.println(attackPoints);

            //funcion atacacar me devuelve la lista de mensajes
            //y en que pos destrui el/los cuadraditos
            //En esta misma respuesta puedo meterle el ATAQUE del server
            //para no tener que levantar server en el cliente con la desventaja que el turno siempre arranca
            //client y cuando llegue la rta, voy a tener que mandar un request oculto con la rta del ataque
            JsonObject res;

            service.setExternalMessage(true);
            //ServiceManager.setResponse(true);
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
