package edu.utn.model.square;

import edu.utn.model.Player;

public class NinjaTrap extends Land {


    public NinjaTrap(int x, int y) {
        super(x, y);
    }

    @Override
    public void playerStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Trampa";
    }

    @Override
    public void fill() {

    }
}
