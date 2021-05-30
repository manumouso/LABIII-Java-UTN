package edu.utn.entity.ninja;


public class NinjaWarrior extends Ninja{


    public NinjaWarrior(String name, int lifePoints, int attackPoints, NinjaPosition ninjaPosition) {
        super(name, lifePoints, attackPoints, ninjaPosition);
    }

    @Override
    public String attackName() {
        return "Warrior Attack";
    }
}
