package edu.utn.manager;


import edu.utn.connection.client.Client;
import edu.utn.connection.server.Server;
import edu.utn.enums.ErrorType;
import edu.utn.error.OperationError;
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
    private int refused;

    private int correctMovement;
    private int killedNinjasCounter;

    private OperationError opError;

    public OperationError getOpError() {
        if(opError==null){
            opError= new OperationError();
        }
        return opError;
    }

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

    public synchronized int getRefused() {
        return refused;
    }

    public synchronized void setRefused(int refused) {
        this.refused = refused;
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
    public NetworkFactory getNetworkFactory() {
        if(networkFactory==null){
            networkFactory=new NetworkFactory();
        }
        return networkFactory;
    }

    public boolean joinGame(String IP, int port, String json){
        boolean success=false;
        try{
            String url="http://"+IP+":"+port+"/join";
            String response = client.post(url,json);
            System.out.println("\t\t\t"+response);
            if(response.equals("Connected to the server")){
                setJoinSuccessful(true);
                setRemoteIp(IP);
                setRemotePort(port);
                success=true;
            }else{
                System.out.println("\t\t\tTry again");
            }
        }catch (Exception e){
            getOpError().add(ErrorType.join.getErrorCode(),ErrorType.join.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public boolean canMove(String json){
        boolean success=false;
        try{
            String url="http://"+getRemoteIp()+":"+getRemotePort()+"/canMove";
            String response = client.post(url,json);

            if(response.equals("Move allowed")){
                success=true;

            }else{
                System.out.println("\t\t\t"+response);
            }
        }catch (Exception e){
            getOpError().add(ErrorType.moveClient.getErrorCode(),ErrorType.moveClient.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public boolean canAttack(String json){
        boolean success=false;
        try{
            String url="http://"+getRemoteIp()+":"+getRemotePort()+"/canAttack";
            String response = client.post(url,json);

            if(response.equals("Attack allowed")){
                success=true;

            }else{
                System.out.println("\t\t\t"+response);
            }
        }catch (Exception e){
            getOpError().add(ErrorType.attackClient.getErrorCode(),ErrorType.attackClient.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public boolean validDirection(String json){
        boolean success=false;
        try{
            String url="http://"+getRemoteIp()+":"+getRemotePort()+"/validDirection";
            String response = client.post(url,json);

            if(response.equals("Valid direction")){

                success=true;
            }else{
                System.out.println("\t\t\t"+response);
            }
        }catch (Exception e){
            getOpError().add(ErrorType.validDirection.getErrorCode(),ErrorType.validDirection.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public boolean attack(NinjaPosition attackPosition, String json){
        boolean success=false;
        try{
            String url="http://"+getRemoteIp()+":"+getRemotePort()+"/attack";
            String response = client.post(url,json);
            System.out.println("\t\t\t"+response);
            if(response.equals("You killed the ninja commander") || response.equals("You killed a ninja warrior")){
                AttackBoard.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
                setCorrectMovement(getCorrectMovement()+1);
                setKilledNinjasCounter(getKilledNinjasCounter()+1);
                success= true;
            }
            if(response.equals("You destroyed a square")){
                AttackBoard.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
                setCorrectMovement(getCorrectMovement()+1);
                success= true;
            }
            if(response.equals("You hurt a ninja")){
                setCorrectMovement(getCorrectMovement()+1);
                success= true;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.sendAttack.getErrorCode(),ErrorType.sendAttack.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }


    }
    public void endTurn(){
        try{
            String url="http://"+getRemoteIp()+":"+getRemotePort()+"/yourTurn";
            String response = client.post(url,"{}");
            System.out.println("\t\t\t"+response);
        }catch (Exception e){
            getOpError().add(ErrorType.endTurn.getErrorCode(),ErrorType.endTurn.getErrorMessage()+e.getMessage());
        }

    }


    public boolean invite(String IP, int port, String json){
        boolean success=false;
        try{
            String url="http://"+IP+":"+port+"/invite";
            String response = client.post(url,json);
            System.out.println("\t\t\t"+response);
            if(response.equals("Connection accepted by the client")){
                setInvitationAccepted(true);
                setRemoteIp(IP);
                setRemotePort(port);
                success= true;
            }else{
                setAnswer("Connection refused");
                System.out.println("\t\t\tTry again");
            }
        }catch (Exception e){
            getOpError().add(ErrorType.invite.getErrorCode(),ErrorType.invite.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public synchronized String invitationReceived(){
        String message=" ";
        try{
            Scanner scanner= new Scanner(System.in);
            System.out.print("\t\t\tDo you want to accept the invitation from: "+getRemoteIp()+" ? [Y/n]->");
            String answer = scanner.next();
            if(answer.equals("y")||answer.equals("Y")){
                setInvitationAccepted(true);
                message= "Connection accepted by the client";
            }else{
                setRefused(getRefused()+5);
                message= "Connection refused";
            }
        }catch (Exception e){
            getOpError().add(ErrorType.invitationReceived.getErrorCode(),ErrorType.invitationReceived.getErrorMessage()+e.getMessage());

        }finally {
            return message;
        }
    }
}
