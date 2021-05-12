package edu.utn.model.ninja;

public enum NinjaType {

    NINJUTSU(0,0,300,"This ninja stands out for having great jutsus"),TAIJUTSU(300,0,0,"This ninja stands out for having powerful attacks")
    ,GENJUTSU(0,300,0,"This ninja stands out for having solid defense"),BALANCED(100,100,100,"This ninja trains every skill to face adversity");


    private int extraAttackPoints;
    private int extraDefensePoints;
    private int extraMagicPoints;
    private String message;


    NinjaType(int attack, int defense, int magic, String msg) {

        this.extraAttackPoints=attack;
        this.extraDefensePoints=defense;
        this.extraMagicPoints=magic;
        this.message=msg;
    }

    public int getExtraAttackPoints() {
        return extraAttackPoints;
    }

    public void setExtraAttackPoints(int extraAttackPoints) {
        this.extraAttackPoints = extraAttackPoints;
    }

    public int getExtraDefensePoints() {
        return extraDefensePoints;
    }

    public void setExtraDefensePoints(int extraDefensePoints) {
        this.extraDefensePoints = extraDefensePoints;
    }

    public int getExtraMagicPoints() {
        return extraMagicPoints;
    }

    public void setExtraMagicPoints(int extraMagicPoints) {
        this.extraMagicPoints = extraMagicPoints;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
