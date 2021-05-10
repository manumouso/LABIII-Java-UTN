package edu.utn.model.ninja.element.genkai;

import edu.utn.model.ninja.element.Earth;
import edu.utn.model.ninja.element.Lightning;

public class Cristal extends KekkeiGenkai {
    //lightning,earth
    private Lightning lightning;
    private Earth earth;

    public Lightning getLightning() {
        return lightning;
    }

    public void setLightning(Lightning lightning) {
        this.lightning = lightning;
    }

    public Earth getEarth() {
        return earth;
    }

    public void setEarth(Earth earth) {
        this.earth = earth;
    }
}
