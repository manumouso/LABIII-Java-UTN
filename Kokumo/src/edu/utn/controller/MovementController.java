package edu.utn.controller;

import edu.utn.enums.MessageType;
import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;


import java.util.ArrayList;
import java.util.List;


//pasarle LOS MENSAJES AL MANAGER
public class MovementController {

    private List<String> standOnMessages;
    private NinjaController ninjaController;

    public List<String> getStandOnMessages() {
        if(standOnMessages==null){
            standOnMessages=new ArrayList<>();
        }
        return standOnMessages;
    }

    public NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController=new NinjaController();
        }
        return ninjaController;
    }

    public void ninjaStandsOn(Ninja ninja){
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].ninjaStandsOn(ninja);
        getNinjaController().checkLifePoints(ninja);
        addStandOnMessages(Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].name());
    }

    public NinjaPosition getNextPosition(Ninja ninja){

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

    private void addStandOnMessages(String name){

        switch (name){
            case "Empty":
                getStandOnMessages().add(MessageType.EMPTY.getMessage());
                break;
            case "Trap":
                getStandOnMessages().add(MessageType.TRAP.getMessage());
                break;
            case "Stone":
                getStandOnMessages().add(MessageType.STONE.getMessage());
                break;
            case "Tree":
                getStandOnMessages().add(MessageType.TREE.getMessage());
                break;
            default:
                break;
        }
    }


}
