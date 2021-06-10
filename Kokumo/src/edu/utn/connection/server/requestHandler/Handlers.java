package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpHandler;
import edu.utn.manager.ServiceManager;

public abstract class Handlers implements HttpHandler {

    protected ServiceManager service;


    public Handlers() {
    }

    public Handlers(ServiceManager service) {
        this.service = service;
    }

}
