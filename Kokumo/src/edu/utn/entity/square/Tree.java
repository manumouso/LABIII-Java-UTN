package edu.utn.entity.square;


import edu.utn.entity.Player;

public class Tree extends Land {

    public Tree(int x, int y) {
        super(x, y);
    }

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Tree";
    }



}
