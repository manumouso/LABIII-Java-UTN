package edu.utn.entity.square;


import edu.utn.entity.Player;

public class NinjaTrap extends Land {


    public NinjaTrap(int x, int y) {
        super(x, y);
    }

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Trap";
    }



}
