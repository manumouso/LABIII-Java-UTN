package edu.utn.factory;

import edu.utn.view.*;

public class ViewFactory {

    public Introduction createIntro(){

        return new Introduction();
    }

    public PrimaryStage createPrimaryStage(){

        return new PrimaryStage();
    }

    public ServerRoom createServerRoom(){

        return new ServerRoom();
    }

    public PlayerRoom createPlayerRoom(){

        return new PlayerRoom();
    }

    public GameRoom createGameRoom(){

        return new GameRoom();
    }

}
