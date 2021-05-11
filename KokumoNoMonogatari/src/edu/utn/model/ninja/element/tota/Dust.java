package edu.utn.model.ninja.element.tota;


import edu.utn.model.Player;
import edu.utn.model.ninja.element.Earth;
import edu.utn.model.ninja.element.Fire;
import edu.utn.model.ninja.element.Wind;
import javafx.scene.paint.Paint;

public class Dust extends KekkeiTota {
    //earth,fire,wind

    private Earth earth;
    private Fire fire;
    private Wind wind;

    public Earth getEarth() {
        return earth;
    }

    public void setEarth(Earth earth) {
        this.earth = earth;
    }

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
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
