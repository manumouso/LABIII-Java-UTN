package edu.utn.manager;

import edu.utn.connection.server.Server;
import edu.utn.controller.BoardController;
import edu.utn.controller.MovementController;
import edu.utn.controller.NinjaController;
import edu.utn.controller.PlayerController;
import edu.utn.enums.NetworkType;
import edu.utn.factory.MenuFactory;
import edu.utn.factory.NetworkFactory;
import edu.utn.factory.NinjaFactory;
import edu.utn.factory.PlayerFactory;
import edu.utn.message.Message;
import edu.utn.enums.MessageType;
import edu.utn.model.Player;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.MovementValidator;
import edu.utn.validator.NetworkValidator;
import edu.utn.view.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameManager {

    private ServiceManager serviceManager;
    private RuleManager ruleManager;
    private PlayerManager playerManager;
    private Message message;

    private BoardController boardController;
    private BoardPrinter boardPrinter;
    private MessagePrinter messagePrinter;
    private Player player;
    private PlayerFactory playerFactory;
    private PlayerController playerController;
    private NinjaFactory ninjaFactory;
    private MovementController movementController;
    private NinjaController ninjaController;
    private MenuFactory menuFactory;

    private Map<String, Direction> directionsMap;

    public GameManager(ServiceManager serviceManager, RuleManager ruleManager, PlayerManager playerManager) {
        this.serviceManager = serviceManager;
        this.ruleManager = ruleManager;
        this.playerManager = playerManager;
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

    private MessagePrinter getMessagePrinter() {
        if(messagePrinter==null){
            messagePrinter= new MessagePrinter();
        }
        return messagePrinter;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private PlayerFactory getPlayerFactory() {
        if(playerFactory==null){
            playerFactory= new PlayerFactory();
        }
        return playerFactory;
    }

    private PlayerController getPlayerController() {
        if(playerController==null){
            playerController= new PlayerController();
        }
        return playerController;
    }

    private NinjaFactory getNinjaFactory() {
        if(ninjaFactory==null){
            ninjaFactory = new NinjaFactory();
        }
        return ninjaFactory;
    }

    private MovementController getMovementController() {
        if(movementController==null){
            movementController=new MovementController();
        }
        return movementController;
    }

    private NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController= new NinjaController();
        }
        return ninjaController;
    }

    private MenuFactory getMenuFactory() {
        if(menuFactory==null){
            menuFactory = new MenuFactory();
        }
        return menuFactory;
    }

    private Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }
    private void addAll(List<String> messages){

        getMessage().getMessageList().addAll(messages);
    }

    public Map<String, Direction> getDirectionsMap() {
        if(directionsMap== null){
            directionsMap=getNinjaFactory().createDirectionMap();
        }
        return directionsMap;
    }

    public void startGame(){
        MenuFactory menuFactory= getMenuFactory();
        Introduction intro = menuFactory.createIntro();
        intro.print();
        PrimaryStage primaryStage = menuFactory.createPrimaryStage();
        primaryStage.menu(this);
    }

    public void toServerRoom(){
        ServerRoom serverRoom = menuFactory.createServerRoom();
        serverRoom.menu(this);
    }
    public void toPlayerRoom(){
        PlayerRoom playerRoom= menuFactory.createPlayerRoom();
        playerRoom.menu(this);
    }
    public void toGameRoom(){
        GameRoom gameRoom = menuFactory.createGameRoom();
        gameRoom.menu(this);
    }


    public void toClientRoom(){
        ClientRoom clientRoom = menuFactory.createClientRoom();
        clientRoom.menu(this);
    }

    public void setServer(String IP,int port){
        getServiceManager().setServer(createServer(IP,port));
    }

    public Server createServer(String IP, int port){
        NetworkFactory networkFactory = getServiceManager().getNetworkFactory();
        if(validIP(IP)){
            if(validPort(port)){
                Server server =networkFactory.createServer(IP,port);
                getMessage().getMessageList().add(NetworkType.SERVER.getMessage());
                return server;
            }
        }
        return null;
    }

    public void startConnection() throws IOException {
        getServiceManager().getServer().startConnection(getServiceManager());
    }

    public void closeConnection() throws IOException {
        getServiceManager().getServer().closeConnection();
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
    public void printMessages(){
        MessagePrinter messagePrinter=getMessagePrinter();
        messagePrinter.printMessages(getMessage().getMessageList());
    }

    public void clearMessages(){
        MessagePrinter messagePrinter = getMessagePrinter();
        messagePrinter.clearMessages(getMessage().getMessageList());
    }


    public void printBoard(boolean playersBoard){
        BoardPrinter boardPrinter=getBoardPrinter();
        if(playersBoard){
            boardPrinter.printBoard(getPlayer());
        }else{
            boardPrinter.printAttackBoard();
        }
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

    public void clearBoards(boolean ninjasBoard){
        BoardController boardController = getBoardController();
        boardController.clearBoards(ninjasBoard);
    }

    public Player createPlayer(){
        PlayerFactory playerFactory= getPlayerFactory();
        return playerFactory.createPlayer();
    }

    public void setPlayerName(String name){
        PlayerController playerController = getPlayerController();
        playerController.setName(getPlayer(),name);
    }

    public void addNinjaToPlayer(Ninja ninja){
        PlayerController playerController=getPlayerController();
        playerController.addNinja(getPlayer(),ninja);

    }
    public void clearNinjas(){
        PlayerController playerController = getPlayerController();
        playerController.clearNinjas(getPlayer());
    }

    public Ninja createNinja(int i,int j,boolean commander,int m){
        if(withinLimits(i,j,true)){
            if(freeSquare(i,j)){
                NinjaFactory ninjaFactory = getNinjaFactory();
                Ninja ninja =ninjaFactory.createNinja(i,j,commander,m);
                MovementController movementController = getMovementController();
                movementController.ninjaStandsOn(ninja);
                addAll(movementController.getStandOnMessages());
                movementController.getStandOnMessages().clear();
                return ninja;
            }
        }

        return null;
    }

    public boolean move(Ninja ninja, Direction direction){

        if(isAlive(ninja)){

                MovementController movementController = getMovementController();
                NinjaController ninjaController = getNinjaController();
                ninjaController.setDirection(ninja,direction);
                NinjaPosition current = ninjaController.getCurrentPosition(ninja);
                NinjaPosition next = movementController.getNextPosition(ninja);

                if(withinLimits(next.getI(), next.getJ())){
                    if (squarePassable(next.getI(), next.getJ())){
                        if (freeSquare(next.getI(), next.getJ())){

                            movementController.move(ninja, current, next);
                            addAll(movementController.getStandOnMessages());
                            movementController.getStandOnMessages().clear();
                            return true;
                        }
                    }
                }

        }
        return false;
    }

    public boolean commanderAlive(){
        if(!MovementValidator.commanderDead(getPlayer())){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DEADCOMMANDER.getMessage());
        return false;
    }
    private boolean withinLimits(int i,int j,boolean create){
        if(MovementValidator.withinLimitsBoard(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.CREATE.getMessage());
        return false;
    }
    private boolean withinLimits(int i,int j){
        if(MovementValidator.withinLimitsBoard(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.OUTBOUNDARY.getMessage());
        return false;
    }
    private boolean freeSquare(int i,int j){
        if(!MovementValidator.squareOccupied(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.OCCUPIED.getMessage());
        return false;
    }

    private boolean isAlive(Ninja ninja){
        if(!ninja.isDead()){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DEAD.getMessage());
        return false;
    }

    public boolean movementAllowed(Ninja ninja){
        if(!MovementValidator.movedPreviousTurn(ninja.getMovementCounter())){
            return true;
        }
        getMessage().getMessageList().add(MessageType.CONSECUTIVE.getMessage());
        return false;
    }

    private boolean squarePassable(int i, int j){
        if(!MovementValidator.squareDestroyed(i, j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DESTROYED.getMessage());
        return false;
    }

    public void attack(){

    }
}
