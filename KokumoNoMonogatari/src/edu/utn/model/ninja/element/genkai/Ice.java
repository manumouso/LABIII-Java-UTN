package edu.utn.model.ninja.element.genkai;

import edu.utn.model.Player;
import edu.utn.model.ninja.element.Water;
import edu.utn.model.ninja.element.Wind;
import javafx.scene.paint.Paint;

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
