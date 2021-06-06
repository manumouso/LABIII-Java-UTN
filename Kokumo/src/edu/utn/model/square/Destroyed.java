package edu.utn.model.square;


import edu.utn.enums.SquareType;
import edu.utn.model.ninja.Ninja;

public class Destroyed extends Land{

    @Override
    public void ninjaStandsOn(Ninja ninja) {

    }

    @Override
    public String name() {
        return SquareType.DESTROYED.getName();
    }
}
