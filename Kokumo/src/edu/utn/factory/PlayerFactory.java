package edu.utn.factory;

import edu.utn.model.Move;
import edu.utn.model.Player;
import edu.utn.model.State;
import edu.utn.model.Wait;

public class PlayerFactory {

    public Player createPlayer(){

        State state =createWait();
        return new Player(state);
    }

    public State createWait(){

        return new Wait();
    }

    public State createMove(){

        return new Move();
    }
}
