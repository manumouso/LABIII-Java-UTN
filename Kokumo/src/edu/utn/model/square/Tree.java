package edu.utn.model.square;


import edu.utn.manager.GameConstants;
import edu.utn.model.ninja.Ninja;

public class Tree extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setAttackPoints(ninja.getAttackPoints()+ GameConstants.TREE_EXTRA_ATTACK_POINTS);
    }
    @Override
    public String name() {
        return SquareType.TREE.getName();
    }


}
