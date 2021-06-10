package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpHandler;
import edu.utn.manager.ServiceManager;

public abstract class Handlers implements HttpHandler {

    protected ServiceManager serviceManager;


    public Handlers() {
    }

    public Handlers(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

}
