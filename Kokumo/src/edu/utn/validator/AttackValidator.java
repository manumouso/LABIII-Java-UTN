package edu.utn.validator;

import edu.utn.entity.Board;
import edu.utn.entity.ninja.Ninja;



public class AttackValidator {
    //estos mensajes tienen que ser DUPLICADOS, los del tablero, le hablan a jugador
    //los otros se guardan en la clase json loka que viaja, en vez de you, oh destruyeron tu square...
    public static void hitNinja(Ninja ninja){
        //esta tengo que resolver el tema de que le paso, si un array o el json array
        //true= ninja.getNinjaPosition().getI()==attackPosition[i].getI() && ninja.getNinjaPosition().getJ()==attackPosition[i].getJ()
        if(true){
            ninja.setLifePoints(ninja.getLifePoints()/*-attackPoints[i]*/);
            String previousName= ninja.getName();
            ninja.checkLifePoints();
            ninjaDead(ninja.getName(),previousName);

        }else{
            Board.getInstance().getMessages().getMessageList().add("You destroyed a square");
            //Board.getInstance().getSquares()[attackPosition[i].getI()][attackPosition[i].getJ()]=new Destroyed();

        }

    }

    public static void ninjaDead(String name, String previousName){
        if(name.equals("dead")){
            if(previousName.equals("NC")){
                Board.getInstance().getMessages().getMessageList().add("You Killed the ninja commander. WINNER");
            }else{
                Board.getInstance().getMessages().getMessageList().add("You Killed a ninja warrior");
            }
        }else{
            Board.getInstance().getMessages().getMessageList().add("You hurt a ninja");
        }
    }
}
