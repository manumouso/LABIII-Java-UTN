package edu.utn.manager;


import edu.utn.controller.PlayerController;
import edu.utn.factory.PlayerFactory;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;

import java.util.Scanner;

public class PlayerManager {

    private Player player;
    private PlayerFactory playerFactory;
    private PlayerController playerController;

    private boolean myTurn;

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

    public boolean winner(GameManager manager){
        if(manager.getServiceManager().getKilledNinjasCounter()==GameConstants.MAX_NINJAS) {

            System.out.println("\t\t\tWINNER: " + getPlayer().getName());
            return true;
        }else{
            return false;
        }
    }

    public boolean lose(){
        int i=0;
        for(Ninja ninja: getPlayer().getNinjas()){
            if(ninja.isDead()){
                i++;
            }
        }
        if(i==GameConstants.MAX_NINJAS){
            System.out.println("\t\t\tLOSER: "+ getPlayer().getName());
            return true;
        }
        return false;
    }
    public void resetCounters(){
        for(Ninja ninja: getPlayer().getNinjas()){
            ninja.setAttackCounter(0);
            ninja.setMovementCounter(0);
        }
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
