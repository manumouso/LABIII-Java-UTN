package edu.utn.model.square;

import edu.utn.model.Player;

public class Stone extends Land {


    public Stone(int x, int y) {
        super(x, y);
    }

    @Override
    public void playerStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Piedra";
    }

    @Override
    public void fill() {

    }
}
