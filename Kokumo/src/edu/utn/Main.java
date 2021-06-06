package edu.utn;

import edu.utn.connection.server.Server;
import edu.utn.factory.MenuFactory;
import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        MenuFactory menuFactory= new MenuFactory();
        menuFactory.startGame(menuFactory);

//        try{
//
//            Server server = new Server("192.168.0.192",8080);
//            server.startConnection();
//
//        }catch (IOException e){
//            e.printStackTrace();
//
//        }
    }
}
