package edu.utn;

import edu.utn.connection.server.Server;
import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

//        Introduction.print();
//        PrimaryStage primaryStage = new PrimaryStage();
//        primaryStage.menu();

        try{

            Server server = new Server("",0);
            server.startConnection();

        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
