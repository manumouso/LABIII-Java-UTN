package edu.utn.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private List<String> messageList;
    private Map<Integer,String> errorMap;

    public List<String> getMessageList() {
        if(messageList== null){
            messageList=new ArrayList<>();
        }
        return messageList;
    }

    public Map<Integer, String> getErrorMap() {
        if(errorMap==null){
            errorMap=new HashMap<>();
        }
        return errorMap;
    }
}
