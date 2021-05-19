package edu.utn.entity.ninja;


import edu.utn.entity.square.Square;

import java.util.List;

public class Ninja {

    private String name;
    private int lifePoints;
    private int attackPoints;
    private int defensePoints;
    private int magicPoints;
    private NinjaType ninjaType;
    private Square currentSquare;
    private List<Attack> attacks;



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

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public NinjaType getNinjaType() {
        return ninjaType;
    }

    public void setNinjaType(NinjaType ninjaType) {
        this.ninjaType = ninjaType;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }
}
