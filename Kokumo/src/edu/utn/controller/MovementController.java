package edu.utn.controller;

import edu.utn.message.MessageType;
import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;


import java.util.HashMap;
import java.util.Map;

//pasarle LOS MENSAJES AL MANAGER
public class MovementController {

    private Map<Integer,String> message;

    public Map<Integer, String> getMessage() {
        if(message==null){
            message= new HashMap<>();
        }
        return message;
    }

    public void ninjaStandsOn(Ninja ninja){
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].ninjaStandsOn(ninja);
        ninja.checkLifePoints();
        addStandOnMessages(Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].name());
    }

    public NinjaPosition calculateNextPosition(Ninja ninja){

        return ninja.getNinjaPosition().next(ninja.getDirection());
    }
    public void move(Ninja ninja, NinjaPosition current, NinjaPosition next){
        Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(false);
        ninja.setNinjaPosition(next);
        ninjaStandsOn(ninja);
        //ninja.movementCounter++; por ahora COMENTADO, PORQUE NO DECIDI EL PUNTO DEL PROGRAMA
        //DONDE ESTE CONTADOR VUELVE A CERO, Y EVITA QUE SE HAGAN MOV CONSECUTIVOS
        //seguramente despues de atacar
    }

    public void addStandOnMessages(String name){

        switch (name){
            case "Empty":
                getMessage().put(MessageType.EMPTY.getMessageNumber(), MessageType.EMPTY.getMessage());
                break;
            case "Trap":
                getMessage().put(MessageType.TRAP.getMessageNumber(), MessageType.TRAP.getMessage());
                break;
            case "Stone":
                getMessage().put(MessageType.STONE.getMessageNumber(), MessageType.STONE.getMessage());
                break;
            case "Tree":
                getMessage().put(MessageType.TREE.getMessageNumber(), MessageType.TREE.getMessage());
                break;
            default:
                break;
        }
    }

}
