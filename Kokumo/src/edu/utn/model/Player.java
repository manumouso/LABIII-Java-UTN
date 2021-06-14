package edu.utn.model;


import edu.utn.model.ninja.Ninja;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Ninja> ninjas;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ninja> getNinjas() {
        if(ninjas==null){
            ninjas=new ArrayList<>();
        }
        return ninjas;
    }

    public void setNinjas(List<Ninja> ninjas) {
        this.ninjas = ninjas;
    }

}
