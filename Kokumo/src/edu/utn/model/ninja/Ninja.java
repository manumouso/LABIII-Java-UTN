package edu.utn.model.ninja;

import javax.json.Json;
import javax.json.JsonObject;


public abstract class Ninja {

    private String name;
    private int lifePoints;
    private int attackPoints;
    private NinjaPosition ninjaPosition;
    private Direction direction;
    private boolean dead;
    private int movementCounter;

    public Ninja(String name, int lifePoints, int attackPoints, NinjaPosition ninjaPosition) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints=attackPoints;
        this.ninjaPosition = ninjaPosition;
        this.dead= false;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public NinjaPosition getNinjaPosition() {
        return ninjaPosition;
    }

    public void setNinjaPosition(NinjaPosition ninjaPosition) {
        this.ninjaPosition = ninjaPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getMovementCounter() {
        return movementCounter;
    }

    public void setMovementCounter(int movementCounter) {
        this.movementCounter = movementCounter;
    }


    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("ninjaName", this.name)
                .add("lifePoints", this.lifePoints)
                .build();
    }

}
