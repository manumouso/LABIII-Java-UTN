package edu.utn.manager;

import edu.utn.connection.client.Client;
import edu.utn.connection.server.Server;
import edu.utn.controller.BoardController;
import edu.utn.enums.NetworkType;
import edu.utn.factory.ViewFactory;
import edu.utn.factory.NetworkFactory;
import edu.utn.message.Message;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.NetworkValidator;
import edu.utn.view.*;
import java.io.IOException;

public class GameManager {

    private boolean host;
    private ServiceManager serviceManager;
    private RuleManager ruleManager;
    private PlayerManager playerManager;
    private ViewFactory viewFactory;
    private BoardController boardController;
    private BoardPrinter boardPrinter;
    private Message message;
    private MessagePrinter messagePrinter;

    public GameManager(ServiceManager serviceManager, RuleManager ruleManager, PlayerManager playerManager) {
        this.serviceManager = serviceManager;
        this.ruleManager = ruleManager;
        this.playerManager = playerManager;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public RuleManager getRuleManager() {
        return ruleManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    private BoardController getBoardController() {
        if(boardController==null){
            boardController = new BoardController();
        }
        return boardController;
    }

    private BoardPrinter getBoardPrinter() {
        if(boardPrinter==null){
            boardPrinter= new BoardPrinter();
        }
        return boardPrinter;
    }

    private synchronized Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }
    private synchronized void addAll(){

        getMessage().getMessageList().addAll(getRuleManager().getMessage().getMessageList());
        getRuleManager().getMessage().getMessageList().clear();
    }

    private MessagePrinter getMessagePrinter() {
        if(messagePrinter==null){
            messagePrinter= new MessagePrinter();
        }
        return messagePrinter;
    }

    private ViewFactory getViewFactory() {
        if(viewFactory ==null){
            viewFactory = new ViewFactory();
        }
        return viewFactory;
    }

    public void startGame(){
        ViewFactory viewFactory = getViewFactory();
        Introduction intro = viewFactory.createIntro();
        intro.print();
        PrimaryStage primaryStage = viewFactory.createPrimaryStage();
        primaryStage.menu(this);
    }

    public void toServerRoom(){
        ServerRoom serverRoom = viewFactory.createServerRoom();
        serverRoom.menu(this);
    }
    public void toPlayerRoom(){
        PlayerRoom playerRoom= viewFactory.createPlayerRoom();
        playerRoom.menu(this);
    }
    public void toGameRoom(){
        GameRoom gameRoom = viewFactory.createGameRoom();
        gameRoom.menu(this);
    }
    public void setServer(int port){
        getServiceManager().setServer(createServer(port));
    }

    public Server createServer(int port){
        Server server = null;
        try {
            NetworkFactory networkFactory = getServiceManager().getNetworkFactory();
            if (validPort(port)) {
                server = networkFactory.createServer(port);
                getMessage().getMessageList().add(NetworkType.SERVER.getMessage());
            }
        }catch (Exception e){
            System.out.println("\t\t\t Exception: "+e.getMessage());
        }finally {
            return server;
        }

    }

    public void setClient(){
        getServiceManager().setClient(createClient());
    }

    public Client createClient(){
        Client client=null;
        try{
            NetworkFactory networkFactory = getServiceManager().getNetworkFactory();
            client =networkFactory.createClient();
            getMessage().getMessageList().add(NetworkType.CLIENT.getMessage());

        }catch (Exception e){
            System.out.println("\t\t\t Exception: "+e.getMessage());
        }finally {
            return client;
        }

    }

    public boolean sendJoin(String IP,int port){
        boolean success=false;
        try{
            if(validIP(IP)){
                if(validPort(port)){
                    int myPort= getServiceManager().getServer().getPort();
                    success= getServiceManager().joinGame(IP,port,"{\"port\":"+myPort+"}");
                }
            }

        }catch (Exception e){
            System.out.println("\t\t\t Exception: "+e.getMessage());
        }finally {
            return success;
        }

    }
    public boolean sendInvitation(String IP,int port){
        boolean success= false;
        try{
            if(validIP(IP)){
                if(validPort(port)){
                    int myPort= getServiceManager().getServer().getPort();
                    success= getServiceManager().invite(IP,port,"{\"port\":"+myPort+"}");
                }
            }
        }catch (Exception e){
            System.out.println("\t\t\t Exception: "+e.getMessage());
        }finally {
            return success;
        }

    }
    public boolean sendAttack(NinjaPosition attackPosition, int attackPoints){
        boolean success= false;
        try {
            String json="{\"position\":["+attackPosition.getI()+","+attackPosition.getJ()+"],\"attackPoints\":"+attackPoints+"}";
            success= getServiceManager().attack(attackPosition,json);
        }catch (Exception e){
            System.out.println("\t\t\t Exception: "+e.getMessage());
        }finally {
            return success;
        }

    }

    public void sendEndTurn(){
        try{
            getServiceManager().endTurn();
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
        }
    }

    public void startConnection(){
        try{
            getServiceManager().getServer().startConnection(getServiceManager(),getRuleManager(),getPlayerManager());
        }catch (IOException e){
            System.out.println("\t\t\tIO Exception: "+e.getMessage());
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
        }
    }

    public void closeConnection(){
        try{
            getServiceManager().getServer().closeConnection();
        }catch (IOException e){
            System.out.println("\t\t\tIO Exception: "+e.getMessage());
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
        }
    }

    public void setServerState(){
        getServiceManager().setServerState(createServerState());
    }
    public ServerState createServerState(){
        return getServiceManager().getServerState();
    }
    public boolean serverWasCreated() {

        return getServiceManager().getServerState().serverWasCreated();
    }
    public void setServerWasCreated(boolean serverWasCreated) {

        getServiceManager().getServerState().setServerWasCreated(serverWasCreated);
    }

    public boolean isRunning() {
        return getServiceManager().getServerState().isRunning();
    }
    public void setRunning(boolean running) {
        getServiceManager().getServerState().setRunning(running);
    }
    public boolean connectedClient() {
        return getServiceManager().getServerState().connectedClient();
    }
    public void setConnectedClient(boolean connectedClient) {
        getServiceManager().getServerState().setConnectedClient(connectedClient);
    }

    public synchronized void setExternalMessage(boolean action){

        getServiceManager().setExternalMessage(action);
    }
    public synchronized boolean externalMessageReceived(){

        return getServiceManager().externalMessageArrived();
    }

    public Server getServer(){

        return getServiceManager().getServer();
    }

    public void printBoard(boolean playersBoard){
        BoardPrinter boardPrinter=getBoardPrinter();
        if(playersBoard){
            boardPrinter.printBoard(playerManager.getPlayer());
        }else{
            boardPrinter.printAttackBoard();
        }
    }
    public void clearBoards(boolean ninjasBoard){
        BoardController boardController = getBoardController();
        boardController.clearBoards(ninjasBoard);
    }
    public void printMessages(){
        MessagePrinter messagePrinter=getMessagePrinter();
        addAll();
        messagePrinter.printMessages(getMessage().getMessageList());
    }

    public void clearMessages(){
        MessagePrinter messagePrinter = getMessagePrinter();
        messagePrinter.clearMessages(getMessage().getMessageList());
    }

    private boolean validIP(String IP){
        if(NetworkValidator.validIP(IP)){
            return true;
        }
        getMessage().getMessageList().add(NetworkType.IP.getMessage());
        return false;
    }

    private boolean validPort(int port){
        if(NetworkValidator.validPort(port)){
            return true;
        }
        getMessage().getMessageList().add(NetworkType.PORT.getMessage());
        return false;
    }

    public synchronized void checkReceivedMessages(){
        try{
            if(getRuleManager().getMessageArrived()>0) {
                printMessages();
                clearMessages();
                getRuleManager().setMessageArrived(0);
            }
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
        }

    }



}
