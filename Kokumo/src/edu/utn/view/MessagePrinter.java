package edu.utn.view;


import java.util.List;

public class MessagePrinter {

    public void printMessages(List<String> messages){
        for(String msg: messages){
            System.out.println("\t\t\t"+msg);
        }
        System.out.println("\n");
    }

    public void clearMessages(List<String> messages){

        messages.clear();

    }
}
