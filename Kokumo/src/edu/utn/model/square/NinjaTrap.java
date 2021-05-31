package edu.utn.model.square;


import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;

public class NinjaTrap extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setLifePoints(ninja.getLifePoints()-1);
        Board.getInstance().getMessages().getMessageList().add("Oh you stepped on a trap, your ninja lose 1 life point. Ninja name: "+ ninja.getName()+" Life points: "+ninja.getLifePoints());
    }

    @Override
    public String name() {
        return "Trap";
    }


}
