package edu.utn.enums;

public enum NinjaType {

    COMMANDER("NC",10,10), WARRIOR("NW",5,5);

    private String name;
    private int lifePoints;
    private int attackPoints;

    NinjaType(String name, int lifePoints, int attackPoints) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints = attackPoints;
    }

    public String getName() {
        return name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }
}
