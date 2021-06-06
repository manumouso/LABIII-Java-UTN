package edu.utn.message;


import java.util.ArrayList;
import java.util.List;

public class Message {

    private List<String> messageList;


    public List<String> getMessageList() {
        if(messageList==null){
            messageList=new ArrayList<>();
        }
        return messageList;
    }
}
