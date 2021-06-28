package edu.utn.manager;


import edu.utn.controller.PlayerController;
import edu.utn.enums.ErrorType;
import edu.utn.error.OperationError;
import edu.utn.factory.PlayerFactory;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;

import java.util.Scanner;

public class PlayerManager {

    private Player player;
    private PlayerFactory playerFactory;
    private PlayerController playerController;

    private boolean myTurn;
    private OperationError opError;

    public OperationError getOpError() {
        if(opError==null){
            opError= new OperationError();
        }
        return opError;
    }

    public synchronized boolean isMyTurn() {
        return myTurn;
    }

    public synchronized void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public synchronized Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerFactory getPlayerFactory() {
        if(playerFactory==null){
            playerFactory= new PlayerFactory();
        }
        return playerFactory;
    }

    public PlayerController getPlayerController() {
        if(playerController==null){
            playerController= new PlayerController();
        }
        return playerController;
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

    public boolean win(GameManager manager){
        boolean success=false;
        try{
            if(manager.getServiceManager().getKilledNinjasCounter()==GameConstants.MAX_NINJAS) {

                System.out.println("\t\t\tWINNER: "+ getPlayer().getName());
                success= true;
            }else{
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.win.getErrorCode(),ErrorType.win.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }

    }

    public boolean lose(){
        boolean success=false;
        try{
            int i;
            i=getDeadNinjaQuantity();
            if(i==GameConstants.MAX_NINJAS){
                System.out.println("\t\t\tLOSER: "+ getPlayer().getName());
                success= true;
            }else{
                success=false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.lose.getErrorCode(),ErrorType.lose.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }


    }

    public void resetCounters(){
        for(Ninja ninja: getPlayer().getNinjas()){
            ninja.setAttackCounter(0);
            ninja.setMovementCounter(0);
        }
    }

    private int getDeadNinjaQuantity(){
        int i=0;
        for(Ninja ninja: getPlayer().getNinjas()){
            if(ninja.isDead()){
                i++;
            }
        }
        return i;
    }

    public int getAliveNinjasQuantity(){
        int i=0;
        for(Ninja ninja:getPlayer().getNinjas()){
            if(!ninja.isDead()){
                i++;
            }
        }
        return i;
    }


}
