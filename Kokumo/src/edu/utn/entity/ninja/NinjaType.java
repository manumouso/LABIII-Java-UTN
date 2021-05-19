package edu.utn.entity.ninja;

public enum NinjaType {

    NINJUTSU(50,50,200,"Este ninja tiene buenos jutsus"),TAIJUTSU(200,50,50,"Este ninja es fuerte en combate cuerpo a cuerpos")
    ,GENJUTSU(50,200,50,"Este ninja tiene solida defensa"),BALANCED(100,100,100,"Este ninja entrena todas las habilidades para enfrentar la adversidad");


    private int attackPoints;
    private int defensePoints;
    private int magicPoints;
    private String message;


    NinjaType(int attack, int defense, int magic, String msg) {

        this.attackPoints=attack;
        this.defensePoints=defense;
        this.magicPoints=magic;
        this.message=msg;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
