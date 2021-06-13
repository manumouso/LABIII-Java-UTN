package edu.utn.manager;

import edu.utn.connection.client.Client;
import edu.utn.connection.client.HttpResponseHandler;
import edu.utn.connection.server.Server;
import edu.utn.factory.NetworkFactory;
import edu.utn.model.AttackBoard;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.model.square.Destroyed;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceManager {

    private Server server;
    private Client client;
    private ServerState serverState;

    private String remoteIp;
    private int remotePort;

    private int correctMovement;

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

    public synchronized String getRemoteIp() {
        return remoteIp;
    }

    public synchronized void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public synchronized int getRemotePort() {
        return remotePort;
    }

    public synchronized void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public synchronized int getCorrectMovement() {
        return correctMovement;
    }

    public synchronized void setCorrectMovement(int correctMovement) {
        this.correctMovement = correctMovement;
    }

    public void sendInvitation(String IP, int port, String json){
        String url="http://"+IP+":"+port+"/join";
        client.post(url, json, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {

                System.out.println("\t\t\t"+content);
                setRequestSuccessful(true);
                setRemoteIp(IP);
                setRemotePort(port);
            }

            @Override
            public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {
                System.out.println("\t\t\t"+Arrays.toString(content));
                setRequestSuccessful(false);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("\t\t\tException");
            }
        });
    }

    public void attack(NinjaPosition attackPosition, int attackPoints,String json){
        String url="http://"+getRemoteIp()+":"+getRemotePort()+"/attack";

        client.post(url, json, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                System.out.println("\t\t\t"+content);
                if(content.equals("You destroyed a square") || content.contains("You killed")){
                    AttackBoard.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
                    setCorrectMovement(getCorrectMovement()+1);
                }
            }

            @Override
            public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {
                System.out.println("\t\t\t"+Arrays.toString(content));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    public void move(){
        String url="http://"+getRemoteIp()+":"+getRemotePort()+"/move";

        client.post(url, " ", new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Map<String, List<String>> headers, String content) {
                setCorrectMovement(getCorrectMovement()+1);
            }

            @Override
            public void onFailure(int statusCode, Map<String, List<String>> headers, byte[] content) {

            }

            @Override
            public void onFailure(Throwable throwable) {

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
