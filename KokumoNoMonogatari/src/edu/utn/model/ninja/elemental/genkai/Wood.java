package edu.utn.model.ninja.elemental.genkai;

import edu.utn.model.ninja.elemental.Earth;
import edu.utn.model.ninja.elemental.Water;

public class Wood extends KekkeiGenkai{
    //water, earth
    private Water water;
    private Earth earth;

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Earth getEarth() {
        return earth;
    }

    public void setEarth(Earth earth) {
        this.earth = earth;
    }
}
