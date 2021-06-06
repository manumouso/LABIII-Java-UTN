package edu.utn.model.square;

import edu.utn.enums.SquareType;
import edu.utn.manager.GameConstants;
import edu.utn.model.ninja.Ninja;

public class NinjaTrap extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setLifePoints(ninja.getLifePoints()- GameConstants.TRAP_HIT_POINTS);
    }

    @Override
    public String name() {
        return SquareType.NINJA_TRAP.getName();
    }


}
