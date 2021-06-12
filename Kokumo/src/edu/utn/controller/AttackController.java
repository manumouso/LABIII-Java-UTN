package edu.utn.controller;

import edu.utn.json.AttackJson;
import edu.utn.model.Board;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.model.square.Destroyed;

import javax.json.*;
import java.util.*;


public class AttackController {

    private NinjaController ninjaController;
    private List<String> attackMessages;

    public NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController=new NinjaController();
        }
        return ninjaController;
    }

    public List<String> getAttackMessages() {
        if(attackMessages==null){
            attackMessages= new ArrayList<>();
        }
        return attackMessages;
    }

    public AttackJson attackReceived(Player player, AttackJson model){
        int i=0;
        String message;
        for(Ninja ninja: player.getNinjas()){
            if(ninjaPositionEqualAttackPosition(ninja,model.getAttackPositions().get(i))){
                message=hitNinja(ninja,model.getAttackPoints().get(i));
            }else{
                message=hitSquare(model.getAttackPositions().get(i));
            }
            model.getMessage().add(message);
            i++;
        }

        return model;
    }

    public boolean ninjaPositionEqualAttackPosition(Ninja ninja, NinjaPosition attackPosition){

        return ninja.getNinjaPosition().getI()==attackPosition.getI() && ninja.getNinjaPosition().getJ()==attackPosition.getJ();
    }
    public String hitNinja(Ninja ninja,int attackPoints){
        ninja.setLifePoints(ninja.getLifePoints()-attackPoints);
        getNinjaController().checkLifePoints(ninja);
        return addHitInfo(ninja);

    }
    public String hitSquare(NinjaPosition attackPosition){
        Board.getInstance().getSquares()[attackPosition.getI()][attackPosition.getJ()]=new Destroyed();
        getAttackMessages().add("Your square ("+attackPosition.getI()+","+attackPosition.getJ()+") was destroyed");
        return "You destroyed a square";
    }
    public String addHitInfo(Ninja ninja){
        String message;
        if(ninja.isDead()){
            if(ninja.getName().equals("NC")){
                message="You killed the ninja commander";
                getAttackMessages().add("Your commander was killed");
            }else{
                message="You killed a ninja warrior";
                getAttackMessages().add("Your warrior"+ninja.getName()+" was killed");
            }
        }else{
            message="You hurt a ninja";
            getAttackMessages().add("Your ninja"+ninja.getName()+" was hurt. Life Points: "+ninja.getLifePoints());
        }
        return message;
    }

    public JsonArray message(String message){
        JsonArrayBuilder builder= Json.createArrayBuilder();
        builder.add(message);
        return builder.build();
    }
    public JsonArray messages(JsonArray msg1,JsonArray msg2,JsonArray msg3){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        builder.add(msg1)
                .add(msg2)
                .add(msg3);
        return builder.build();
    }
    public JsonArray attackPoint(int attackPoint){
        JsonArrayBuilder builder= Json.createArrayBuilder();
        builder.add(attackPoint);
        return builder.build();
    }
    public JsonArray points(JsonArray atta1,JsonArray atta2,JsonArray atta3){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        builder.add(atta1)
                .add(atta2)
                .add(atta3);
        return builder.build();
    }
    public JsonArray position(int i, int j){
        JsonArrayBuilder builder= Json.createArrayBuilder();
        builder.add(i).add(j);
        return builder.build();
    }

    public JsonArray positions(JsonArray pos1,JsonArray pos2,JsonArray pos3){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        builder.add(pos1)
                .add(pos2)
                .add(pos3);
        return builder.build();
    }

    public JsonObject sendAttack(AttackJson attackJson){
        JsonArray pos1= position(attackJson.getAttackPositions().get(0).getI(),attackJson.getAttackPositions().get(0).getJ());
        JsonArray pos2= position(attackJson.getAttackPositions().get(1).getI(),attackJson.getAttackPositions().get(1).getJ());
        JsonArray pos3= position(attackJson.getAttackPositions().get(2).getI(),attackJson.getAttackPositions().get(2).getJ());

        JsonArray positions=positions(pos1,pos2,pos3);

        JsonArray atta1= attackPoint(attackJson.getAttackPoints().get(0));
        JsonArray atta2= attackPoint(attackJson.getAttackPoints().get(1));
        JsonArray atta3= attackPoint(attackJson.getAttackPoints().get(2));

        JsonArray points= points(atta1,atta2,atta3);

        JsonArray msg1= message(attackJson.getMessage().get(0));
        JsonArray msg2= message(attackJson.getMessage().get(1));
        JsonArray msg3= message(attackJson.getMessage().get(2));

        JsonArray messages= messages(msg1,msg2,msg3);

        return Json.createObjectBuilder()
                .add("positions",positions)
                .add("points",points)
                .add("messages",messages)
                .build();
    }

}
