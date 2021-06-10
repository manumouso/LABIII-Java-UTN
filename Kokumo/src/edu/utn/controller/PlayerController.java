package edu.utn.controller;

import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;

public class PlayerController {

    public void setName(Player player, String name){

        player.setName(name);
    }

    public void addNinja(Player player, Ninja ninja){

        player.getNinjas().add(ninja);
    }

    public void clearNinjas(Player player){

        player.getNinjas().clear();
    }
}
