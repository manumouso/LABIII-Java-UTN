package edu.utn.model.square;

import edu.utn.model.Player;

public class Empty extends Land{


    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public void playerStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Vacio";
    }

    @Override
    public void fill() {

    }
}
