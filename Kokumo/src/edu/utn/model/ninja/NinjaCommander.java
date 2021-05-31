package edu.utn.model.ninja;


public class NinjaCommander extends Ninja{


    public NinjaCommander(String name, int lifePoints, int attackPoints, NinjaPosition ninjaPosition) {
        super(name, lifePoints, attackPoints, ninjaPosition);
    }

    @Override
    public String attackName() {
        return "Commander Attack";
    }
}
