package edu.utn.controller;

import edu.utn.enums.MessageType;
import edu.utn.enums.SquareType;
import edu.utn.manager.GameManager;
import edu.utn.manager.ServiceManager;
import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;

import java.util.ArrayList;
import java.util.List;

public class MovementController {

    private NinjaController ninjaController;
    private List<String> standOnMessages;

    public NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController=new NinjaController();
        }
        return ninjaController;
    }
    public List<String> getStandOnMessages() {
        if(standOnMessages==null){
            standOnMessages=new ArrayList<>();
        }
        return standOnMessages;
    }


    public void ninjaStandsOn(Ninja ninja){
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].ninjaStandsOn(ninja);
        getNinjaController().checkLifePoints(ninja);
        addStandOnMessages(Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].name());
    }
    public void ninjaStandsOn(Ninja ninja, ServiceManager manager){
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].ninjaStandsOn(ninja);
        getNinjaController().checkLifePoints(ninja);
        addStandOnMessages(Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].name());
        if(ninjaDiedStandingOnTrap(ninja)){
            String url="http://"+manager.getRemoteIp()+":"+manager.getRemotePort()+"/deadByTrap";
            String response= manager.getClient().post(url,"{}");
            System.out.println("\t\t\t"+response);
        }
    }


    private boolean ninjaDiedStandingOnTrap(Ninja ninja){

        return (ninja.isDead() && Board.getInstance().getSquares()[ninja.getNinjaPosition().getI()][ninja.getNinjaPosition().getJ()].name().equals(SquareType.NINJA_TRAP.getName()));
    }

    public NinjaPosition getNextPosition(Ninja ninja){

        return ninja.getNinjaPosition().next(ninja.getDirection());
    }
    public void move(Ninja ninja, NinjaPosition current, NinjaPosition next, ServiceManager manager){
        Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(false);
        ninja.setNinjaPosition(next);
        ninja.setMovementCounter(ninja.getMovementCounter()+1);
        ninjaStandsOn(ninja,manager);

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
