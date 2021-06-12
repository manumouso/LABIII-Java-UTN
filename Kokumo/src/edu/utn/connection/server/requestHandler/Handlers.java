package edu.utn.connection.server.requestHandler;

import com.sun.net.httpserver.HttpHandler;
import edu.utn.manager.PlayerManager;
import edu.utn.manager.RuleManager;
import edu.utn.manager.ServiceManager;

public abstract class Handlers implements HttpHandler {

    protected ServiceManager serviceManager;
    protected PlayerManager playerManager;
    protected RuleManager ruleManager;

    public Handlers() {
    }

    public Handlers(RuleManager ruleManager, PlayerManager playerManager) {
        this.ruleManager = ruleManager;
        this.playerManager=playerManager;
    }

    public Handlers(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

}
