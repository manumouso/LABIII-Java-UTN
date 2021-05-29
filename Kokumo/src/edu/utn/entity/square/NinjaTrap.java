package edu.utn.entity.square;


import edu.utn.entity.Board;
import edu.utn.entity.ninja.Ninja;

public class NinjaTrap extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setLifePoints(ninja.getLifePoints()-1);
        Board.getInstance().getMessages().add("Oh you stepped on a trap, your ninja lose 1 life point. Ninja name: "+ ninja.getName()+" Life points: "+ninja.getLifePoints());
    }

    @Override
    public String name() {
        return "Trap";
    }


}
