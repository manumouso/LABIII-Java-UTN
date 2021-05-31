package edu.utn.model.square;


import edu.utn.model.ninja.Ninja;

public class Destroyed extends Land{

    @Override
    public void ninjaStandsOn(Ninja ninja) {

    }

    @Override
    public String name() {
        return "Destroyed";
    }
}
