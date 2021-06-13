package edu.utn.manager;

import edu.utn.connection.client.Client;
import edu.utn.connection.client.HttpResponseHandler;
import edu.utn.connection.server.Server;
import edu.utn.factory.NetworkFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceManager {

    private Server server;
    private Client client;
    private ServerState serverState;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

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

    public synchronized boolean externalMessageArrived() {
        return externalMessage;
    }

    public synchronized void setExternalMessage(boolean externalMessage) {
        this.externalMessage = externalMessage;
    }

    private boolean requestSuccessful;

    public synchronized boolean isRequestSuccessful() {
        return requestSuccessful;
    }

    public synchronized void setRequestSuccessful(boolean requestSuccessful) {
        this.requestSuccessful = requestSuccessful;
    }


    public void sendInvitation(String IP, int port, String json){
        String url="http://"+IP+":"+port+"/join";
        client.post(url, json, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, byte[] content) {

                System.out.println(Arrays.toString(content));
                setRequestSuccessful(true);
            }

            @Override
            public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {
                System.out.println(Arrays.toString(content));
                setRequestSuccessful(false);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("\t\t\tException");
            }
        });
    }
    public NetworkFactory getNetworkFactory() {
        if(networkFactory==null){
            networkFactory=new NetworkFactory();
        }
        return networkFactory;
    }
}
