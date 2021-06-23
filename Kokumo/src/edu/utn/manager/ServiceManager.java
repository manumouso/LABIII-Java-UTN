package edu.utn.manager;


import edu.utn.connection.client.Client;
import edu.utn.connection.server.Server;
import edu.utn.factory.NetworkFactory;
import edu.utn.model.AttackBoard;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.model.square.Destroyed;

import java.util.Scanner;

public class ServiceManager {

    private Server server;
    private Client client;
    private ServerState serverState;
    private NetworkFactory networkFactory;

    private String remoteIp;
    private int remotePort;

    private boolean externalMessage;
    private boolean joinSuccessful;
    private boolean invitationAccepted;
    private String answer;

    private int correctMovement;
    private int killedNinjasCounter;

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

    public synchronized boolean externalMessageArrived() {
        return externalMessage;
    }

    public synchronized void setExternalMessage(boolean externalMessage) {
        this.externalMessage = externalMessage;
    }

    public synchronized boolean isJoinSuccessful() {
        return joinSuccessful;
    }

    public synchronized void setJoinSuccessful(boolean joinSuccessful) {
        this.joinSuccessful = joinSuccessful;
    }

    public synchronized boolean isInvitationAccepted() {
        return invitationAccepted;
    }

    public synchronized void setInvitationAccepted(boolean invitationAccepted) {
        this.invitationAccepted = invitationAccepted;
    }

    public synchronized String getAnswer() {
        return answer;
    }

    public synchronized void setAnswer(String answer) {
        this.answer = answer;
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

    public synchronized int getKilledNinjasCounter() {
        return killedNinjasCounter;
    }

    public synchronized void setKilledNinjasCounter(int killedNinjasCounter) {
        this.killedNinjasCounter = killedNinjasCounter;
    }

    public boolean joinGame(String IP, int port, String json){
        String url="http://"+IP+":"+port+"/join";
        String response = client.post(url,json);
        System.out.println("\t\t\t"+response);
        if(response.equals("Connected to the server")){
            setJoinSuccessful(true);
            setRemoteIp(IP);
            setRemotePort(port);
            return true;
        }else{
            System.out.println("\t\t\tTry again");
        }

        return false;
    }

    public boolean attack(NinjaPosition attackPosition, String json){
        String url="http://"+getRemoteIp()+":"+getRemotePort()+"/attack";
        String response = client.post(url,json);
        if(response.equals("You killed the ninja commander") || response.equals("You killed a ninja warrior")){
            AttackBoard.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
            setCorrectMovement(getCorrectMovement()+1);
            setKilledNinjasCounter(getKilledNinjasCounter()+1);
            System.out.println("\t\t\t"+response);
            return true;
        }
        if(response.equals("You destroyed a square")){
            AttackBoard.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
            setCorrectMovement(getCorrectMovement()+1);
            System.out.println("\t\t\t"+response);
            return true;
        }
        if(response.equals("You hurt a ninja")){
            setCorrectMovement(getCorrectMovement()+1);
            System.out.println("\t\t\t"+response);
            return true;
        }
        System.out.println("\t\t\t"+response);
        return false;

    }
    public void endTurn(){
        String url="http://"+getRemoteIp()+":"+getRemotePort()+"/yourTurn";
        String response = client.post(url,"{}");
        System.out.println("\t\t\t"+response);
    }
    public NetworkFactory getNetworkFactory() {
        if(networkFactory==null){
            networkFactory=new NetworkFactory();
        }
        return networkFactory;
    }

    public boolean invite(String IP, int port, String json){
        String url="http://"+IP+":"+port+"/invite";
        String response = client.post(url,json);
        System.out.println("\t\t\t"+response);
        if(response.equals("Connection accepted by the client")){
            setInvitationAccepted(true);
            setRemoteIp(IP);
            setRemotePort(port);
            return true;
        }else{
            setAnswer("Connection refused");
            System.out.println("\t\t\tTry again");
        }

        return false;
    }

    public synchronized String invitationReceived(){
        Scanner scanner= new Scanner(System.in);
        System.out.print("\t\t\tDo you want to accept the invitation from: "+getRemoteIp()+" ? [Y/n]->");
        String answer = scanner.next();
        if(answer.equals("y")||answer.equals("Y")){
            setInvitationAccepted(true);
            return "Connection accepted by the client";
        }else{
            return "Connection refused";
        }
    }
}
