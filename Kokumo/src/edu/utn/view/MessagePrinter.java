package edu.utn.view;

import edu.utn.manager.GameManager;

public class MessagePrinter {

    public void printMessages(GameManager gameManager){
        for(String error: gameManager.getMessage().getMessageList()){
            System.out.println("\t\t     "+error);
        }
        System.out.println("\n");
    }
}
