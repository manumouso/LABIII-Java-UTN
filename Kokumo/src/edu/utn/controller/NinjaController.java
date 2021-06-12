package edu.utn.controller;

import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;

public class NinjaController {

    public synchronized void checkLifePoints(Ninja ninja){
        if(ninja.getLifePoints()<=0){
            ninja.setDead(true);
            ninja.setName(ninja.getName()+"D");
        }
    }

    public void setDirection(Ninja ninja, Direction direction){
        ninja.setDirection(direction);
    }

    public NinjaPosition getCurrentPosition(Ninja ninja){

        return ninja.getNinjaPosition();
    }
}
