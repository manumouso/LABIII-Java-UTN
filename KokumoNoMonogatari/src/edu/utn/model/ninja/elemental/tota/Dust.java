package edu.utn.model.ninja.elemental.tota;


import edu.utn.model.ninja.elemental.Earth;
import edu.utn.model.ninja.elemental.Fire;
import edu.utn.model.ninja.elemental.Wind;

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
}
