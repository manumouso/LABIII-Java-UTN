package edu.utn.controller;

import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;

public class NinjaController {

    public void checkLifePoints(Ninja ninja){
        if(ninja.getLifePoints()<=0){
            ninja.setDead(true);
        }
    }

    public void setDirection(Ninja ninja, Direction direction){
        ninja.setDirection(direction);
    }

    public NinjaPosition getCurrentPosition(Ninja ninja){

        return ninja.getNinjaPosition();
    }
}
