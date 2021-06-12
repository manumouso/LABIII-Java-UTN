package edu.utn.controller;


import edu.utn.model.Board;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.model.square.Destroyed;


import java.util.*;


public class AttackController {

    private NinjaController ninjaController;
    private List<String> attackMessages;

    public synchronized NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController=new NinjaController();
        }
        return ninjaController;
    }

    public synchronized List<String> getAttackMessages() {
        if(attackMessages==null){
            attackMessages= new ArrayList<>();
        }
        return attackMessages;
    }

    public synchronized String attackReceived(Player player, NinjaPosition attackPosition,int attackPoints){
        int i=0;
        String message = null;
        for(Ninja ninja: player.getNinjas()){
            if(ninjaPositionEqualAttackPosition(ninja,attackPosition)) {
                message = hitNinja(ninja, attackPoints);
                i++;
            }
        }
        if(i==0){
            message=hitSquare(attackPosition);
        }
        return message;
    }

    public synchronized boolean ninjaPositionEqualAttackPosition(Ninja ninja, NinjaPosition attackPosition){

        return ninja.getNinjaPosition().getI()==attackPosition.getI() && ninja.getNinjaPosition().getJ()==attackPosition.getJ();
    }
    public synchronized String hitNinja(Ninja ninja,int attackPoints){
        ninja.setLifePoints(ninja.getLifePoints()-attackPoints);
        getNinjaController().checkLifePoints(ninja);
        return addHitInfo(ninja);

    }
    public synchronized String hitSquare(NinjaPosition attackPosition){
        Board.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
        getAttackMessages().add("Your square ("+attackPosition.getI()+","+attackPosition.getJ()+") was destroyed");
        return "You destroyed a square";
    }
    public synchronized String addHitInfo(Ninja ninja){
        String message;
        if(ninja.isDead()){
            if(ninja.getName().equals("NC")){
                message="You killed the ninja commander";
                getAttackMessages().add("Your commander was killed");
            }else{
                message="You killed a ninja warrior";
                getAttackMessages().add(ninja.getName()+" was killed");
            }
        }else{
            message="You hurt a ninja";
            getAttackMessages().add(ninja.getName()+" was hurt. Life Points: "+ninja.getLifePoints());
        }
        return message;
    }

}
