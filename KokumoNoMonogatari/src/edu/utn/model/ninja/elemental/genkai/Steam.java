package edu.utn.model.ninja.elemental.genkai;

import edu.utn.model.ninja.elemental.Fire;
import edu.utn.model.ninja.elemental.Wind;

public class Steam extends KekkeiGenkai{
    //fire,wind
    private Fire fire;
    private Wind wind;

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
