package edu.utn.factory;

import edu.utn.enums.NinjaType;
import edu.utn.model.ninja.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NinjaFactory {


    public Ninja createNinja(int i, int j, boolean commander,int m){

        NinjaPosition position=createPosition(i,j);

        if(commander){
            return new NinjaCommander(NinjaType.COMMANDER.getName(),NinjaType.COMMANDER.getLifePoints(),NinjaType.COMMANDER.getAttackPoints(),position);
        }else{
            return new NinjaWarrior(NinjaType.WARRIOR.getName()+m,NinjaType.WARRIOR.getLifePoints(),NinjaType.WARRIOR.getAttackPoints(),position);
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

    public Map<String,Direction> createDirectionMap(){
        Map<String,Direction> directionMap = new HashMap<>();
        directionMap.put("N",Direction.getNorth());
        directionMap.put("n",Direction.getNorth());
        directionMap.put("NORTH",Direction.getNorth());
        directionMap.put("north",Direction.getNorth());
        directionMap.put("NE",Direction.getNorthEast());
        directionMap.put("ne",Direction.getNorthEast());
        directionMap.put("NORTH EAST",Direction.getNorthEast());
        directionMap.put("north east",Direction.getNorthEast());
        directionMap.put("NW",Direction.getNorthWest());
        directionMap.put("nw",Direction.getNorthWest());
        directionMap.put("NORTH WEST",Direction.getNorthWest());
        directionMap.put("north west",Direction.getNorthWest());
        directionMap.put("S",Direction.getSouth());
        directionMap.put("s",Direction.getSouth());
        directionMap.put("SOUTH",Direction.getSouth());
        directionMap.put("south",Direction.getSouth());
        directionMap.put("SE",Direction.getSouthEast());
        directionMap.put("se",Direction.getSouthEast());
        directionMap.put("SOUTH EAST",Direction.getSouthEast());
        directionMap.put("south east",Direction.getSouthEast());
        directionMap.put("SW",Direction.getSouthWest());
        directionMap.put("sw",Direction.getSouthWest());
        directionMap.put("SOUTH WEST",Direction.getSouthWest());
        directionMap.put("south west",Direction.getSouthWest());
        directionMap.put("E",Direction.getEast());
        directionMap.put("e",Direction.getEast());
        directionMap.put("EAST",Direction.getEast());
        directionMap.put("east",Direction.getEast());
        directionMap.put("W",Direction.getWest());
        directionMap.put("w",Direction.getWest());
        directionMap.put("WEST",Direction.getWest());
        directionMap.put("west",Direction.getWest());
        return directionMap;
    }
}
