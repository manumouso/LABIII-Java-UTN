package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpHandler;
import edu.utn.manager.ServiceManager;

public abstract class Handlers implements HttpHandler {

    public ServiceManager service;


    public Handlers() {
    }

    public Handlers(ServiceManager service) {
        this.service = service;
    }

}
