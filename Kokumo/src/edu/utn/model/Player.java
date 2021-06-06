package edu.utn.model;


import edu.utn.model.ninja.Ninja;

import java.util.List;

public class Player {

    private int id;
    private String name;
    private State state;
    private List<Ninja> ninjas;

    public Player(int id, String name, State state, List<Ninja> ninjas) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.ninjas = ninjas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return ninjas;
    }

    public void setNinjas(List<Ninja> ninjas) {
        this.ninjas = ninjas;
    }

}
