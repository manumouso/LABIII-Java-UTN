package edu.utn.model.ninja.element.genkai;

import edu.utn.model.Player;
import edu.utn.model.ninja.element.Earth;
import edu.utn.model.ninja.element.Water;
import javafx.scene.paint.Paint;

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

    @Override
    public void ninjaAttack(Player player) {

    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Paint color() {
        return null;
    }
}
