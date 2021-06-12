package edu.utn.manager;


import edu.utn.controller.PlayerController;
import edu.utn.factory.PlayerFactory;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;

public class PlayerManager {

    private Player player;
    private PlayerFactory playerFactory;
    private PlayerController playerController;

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

}
