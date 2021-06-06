package edu.utn.factory;

import edu.utn.enums.NinjaType;
import edu.utn.model.ninja.*;

import java.util.ArrayList;
import java.util.List;


public class NinjaFactory {


    public Ninja createNinja(String name, int i, int j){

        NinjaPosition position=createPosition(i,j);

        if(name.equals(NinjaType.COMMANDER.getName())){
            return new NinjaCommander(NinjaType.COMMANDER.getName(),NinjaType.COMMANDER.getLifePoints(),NinjaType.COMMANDER.getAttackPoints(),position);
        }else{
            return new NinjaWarrior(NinjaType.WARRIOR.getName(),NinjaType.WARRIOR.getLifePoints(),NinjaType.WARRIOR.getAttackPoints(),position);
        }

    }

    private NinjaPosition createPosition(int i, int j){

        return new NinjaPosition(i,j);
    }


    public List<Ninja> createListNinja(Ninja ninja1, Ninja ninja2, Ninja ninja3){
        List<Ninja> ninjas= new ArrayList<>();
        ninjas.add(ninja1);
        ninjas.add(ninja2);
        ninjas.add(ninja3);

        return ninjas;
    }

}
