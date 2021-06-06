package edu.utn.model;


import edu.utn.model.ninja.Ninja;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private State state;
    private List<Ninja> ninjas;

    public Player(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
