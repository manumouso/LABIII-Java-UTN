package edu.utn.entity.square;


import edu.utn.entity.ninja.Ninja;

public class Destroyed extends Land{

    @Override
    public void ninjaStandsOn(Ninja ninja) {

    }

    @Override
    public String name() {
        return "Destroyed";
    }
}
