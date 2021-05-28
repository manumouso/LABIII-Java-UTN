package edu.utn.entity.square;


import edu.utn.entity.Player;

public class Empty extends Land {

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Empty";
    }


}
