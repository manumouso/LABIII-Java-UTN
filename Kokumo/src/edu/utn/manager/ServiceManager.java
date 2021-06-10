package edu.utn.manager;

import edu.utn.connection.server.Server;
import edu.utn.factory.NetworkFactory;

public class ServiceManager {

    private Server server;
    private ServerState serverState;

    public ServerState getServerState() {
        if(serverState==null){
            serverState = new ServerState();
        }
        return serverState;
    }

    public void setServerState(ServerState serverState) {
        this.serverState = serverState;
    }

    private NetworkFactory networkFactory;

    private boolean externalMessage;

    public synchronized boolean isExternalMessage() {
        return externalMessage;
    }

    public synchronized void setExternalMessage(boolean externalMessage) {
        this.externalMessage = externalMessage;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    //ESTAS TRES FUNCIONARON CUANDO LES QUISE PEGAR DESDE EL HANDLER sin Pasarle por parametro este Manager
    //public static boolean response;

//    public static synchronized boolean isResponse() {
//        return response;
//    }
//
//    public static synchronized void setResponse(boolean response) {
//        ServiceManager.response = response;
//    }

    public NetworkFactory getNetworkFactory() {
        if(networkFactory==null){
            networkFactory=new NetworkFactory();
        }
        return networkFactory;
    }
}
