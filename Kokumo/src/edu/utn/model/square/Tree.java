package edu.utn.model.square;


import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;

public class Tree extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        ninja.setAttackPoints(ninja.getAttackPoints()+1);
        Board.getInstance().getMessages().getMessageList().add("The ninja climbs the tree and gains an extra point of attack. Ninja name: "+ ninja.getName()+" Attack points: "+ninja.getAttackPoints());
    }
    @Override
    public String name() {
        return "Tree";
    }


}
