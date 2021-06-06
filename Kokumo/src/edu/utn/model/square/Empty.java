package edu.utn.model.square;

import edu.utn.enums.SquareType;
import edu.utn.model.ninja.Ninja;


public class Empty extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
    }

    @Override
    public String name() {
        return SquareType.EMPTY.getName();
    }


}
