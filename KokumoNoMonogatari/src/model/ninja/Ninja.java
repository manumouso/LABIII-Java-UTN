package model.ninja;

import model.square.Square;

import java.util.List;

public class Ninja {

    private String name;
    private int lifePoints;
    private int chakraGauge;
    private int attackPoints;
    private int defensePoints;
    private int magicPoints;
    private NinjaType ninjaType;
    private ChakraType chakraType;
    private SpecialChakraType specialChakraType;
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

    public int getChakraGauge() {
        return chakraGauge;
    }

    public void setChakraGauge(int chakraGauge) {
        this.chakraGauge = chakraGauge;
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

    public ChakraType getChakraType() {
        return chakraType;
    }

    public void setChakraType(ChakraType chakraType) {
        this.chakraType = chakraType;
    }

    public SpecialChakraType getSpecialChakraType() {
        return specialChakraType;
    }

    public void setSpecialChakraType(SpecialChakraType specialChakraType) {
        this.specialChakraType = specialChakraType;
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
