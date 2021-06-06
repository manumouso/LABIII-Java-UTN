package edu.utn.factory;

import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

public class MenuFactory {

    public Introduction createIntro(){

        return new Introduction();
    }

    public PrimaryStage createPrimaryStage(){

        return new PrimaryStage();
    }

    public void createJoinRoom(){

    }

    public void createServerRoom(){

    }

    public void createInputRoom(){

    }

    public void createGameRoom(){

    }

    public void startGame(MenuFactory menuFactory){
        Introduction intro = menuFactory.createIntro();
        intro.print();
        PrimaryStage primaryStage = menuFactory.createPrimaryStage();
        primaryStage.menu();
    }
}
