package edu.utn.validator;

import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;

//agregar la funcion que valida attackpoints negativos
public class AttackValidator {
    //estas funciones mas que validadores parecen ser parte AttackController
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
