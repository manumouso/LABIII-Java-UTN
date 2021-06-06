package edu.utn.message;


import java.util.HashMap;

import java.util.Map;

public class Message {

    private Map<Integer,String> messageMap;

    public Map<Integer, String> getMessageMap() {
        if(messageMap==null){
            messageMap=new HashMap<>();
        }
        return messageMap;
    }
}
