package edu.utn.model.ninja.elemental.genkai;

import edu.utn.model.ninja.elemental.Water;
import edu.utn.model.ninja.elemental.Wind;

public class Ice extends KekkeiGenkai {
    //water,wind
    private Water water;
    private Wind wind;

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
