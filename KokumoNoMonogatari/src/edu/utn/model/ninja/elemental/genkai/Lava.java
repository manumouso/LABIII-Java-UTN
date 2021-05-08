package edu.utn.model.ninja.elemental.genkai;

import edu.utn.model.ninja.elemental.Earth;
import edu.utn.model.ninja.elemental.Fire;

public class Lava extends KekkeiGenkai{
    //fire,earth
    private Fire fire;
    private Earth earth;

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public Earth getEarth() {
        return earth;
    }

    public void setEarth(Earth earth) {
        this.earth = earth;
    }
}
