package edu.utn;


import edu.utn.manager.GameManager;
import edu.utn.validator.NetworkValidator;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        GameManager manager= new GameManager();
        manager.startGame();

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
