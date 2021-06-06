package edu.utn.model.square;



import edu.utn.enums.SquareType;
import edu.utn.manager.GameConstants;
import edu.utn.model.ninja.Ninja;

public class Stone extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setLifePoints(ninja.getLifePoints()+ GameConstants.STONE_RECOVERY_POINTS);
    }
    @Override
    public String name() {
        return SquareType.STONE.getName();
    }



}
