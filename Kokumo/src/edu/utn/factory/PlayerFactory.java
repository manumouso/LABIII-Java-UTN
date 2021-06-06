package edu.utn.factory;

import edu.utn.model.Player;
import edu.utn.model.State;
import edu.utn.model.ninja.Ninja;

import java.util.List;

public class PlayerFactory {

    public Player createPlayer(int id, String name, State state, List<Ninja> ninjas){

        return new Player(id,name,state,ninjas);
    }
}
