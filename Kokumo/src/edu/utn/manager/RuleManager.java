package edu.utn.manager;

import edu.utn.controller.AttackController;
import edu.utn.controller.MovementController;
import edu.utn.controller.NinjaController;
import edu.utn.enums.MessageType;
import edu.utn.factory.NinjaFactory;
import edu.utn.message.Message;
import edu.utn.model.Player;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.MovementValidator;

import java.util.List;
import java.util.Map;

public class RuleManager {

    private NinjaFactory ninjaFactory;
    private MovementController movementController;
    private AttackController attackController;
    private NinjaController ninjaController;
    private Map<String, Direction> directionsMap;
    private Message message;

    public NinjaFactory getNinjaFactory() {
        if(ninjaFactory==null){
            ninjaFactory = new NinjaFactory();
        }
        return ninjaFactory;
    }
    private MovementController getMovementController() {
        if(movementController==null){
            movementController=new MovementController();
        }
        return movementController;
    }

    public AttackController getAttackController() {
        if(attackController==null){
            attackController=new AttackController();
        }
        return attackController;
    }

    private NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController= new NinjaController();
        }
        return ninjaController;
    }
    public Map<String, Direction> getDirectionsMap() {
        if(directionsMap== null){
            directionsMap=getNinjaFactory().createDirectionMap();
        }
        return directionsMap;
    }
    public Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }
    private void addAll(List<String> messages){

        getMessage().getMessageList().addAll(messages);
    }

    public Ninja createNinja(int i, int j, boolean commander, int m){
        if(withinLimits(i,j,true)){
            if(freeSquare(i,j)){
                NinjaFactory ninjaFactory = getNinjaFactory();
                Ninja ninja =ninjaFactory.createNinja(i,j,commander,m);
                MovementController movementController = getMovementController();
                movementController.ninjaStandsOn(ninja);
                addAll(movementController.getStandOnMessages());
                movementController.getStandOnMessages().clear();
                return ninja;
            }
        }

        return null;
    }

    public boolean move(Ninja ninja, Direction direction){

        if(isAlive(ninja)){

            MovementController movementController = getMovementController();
            NinjaController ninjaController = getNinjaController();
            ninjaController.setDirection(ninja,direction);
            NinjaPosition current = ninjaController.getCurrentPosition(ninja);
            NinjaPosition next = movementController.getNextPosition(ninja);

            if(withinLimits(next.getI(), next.getJ())){
                if (squarePassable(next.getI(), next.getJ())){
                    if (freeSquare(next.getI(), next.getJ())){

                        movementController.move(ninja, current, next);
                        addAll(movementController.getStandOnMessages());
                        movementController.getStandOnMessages().clear();
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean commanderAlive(Player player){
        if(!MovementValidator.commanderDead(player)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DEADCOMMANDER.getMessage());
        return false;
    }
    private boolean withinLimits(int i,int j,boolean create){
        if(MovementValidator.withinLimitsBoard(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.CREATE.getMessage());
        return false;
    }
    private boolean withinLimits(int i,int j){
        if(MovementValidator.withinLimitsBoard(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.OUTBOUNDARY.getMessage());
        return false;
    }
    private boolean freeSquare(int i,int j){
        if(!MovementValidator.squareOccupied(i,j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.OCCUPIED.getMessage());
        return false;
    }
    private boolean isAlive(Ninja ninja){
        if(!ninja.isDead()){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DEAD.getMessage());
        return false;
    }
    public boolean movementAllowed(Ninja ninja){
        if(!MovementValidator.movedPreviousTurn(ninja.getMovementCounter())){
            return true;
        }
        getMessage().getMessageList().add(MessageType.CONSECUTIVE.getMessage());
        return false;
    }
    private boolean squarePassable(int i, int j){
        if(!MovementValidator.squareDestroyed(i, j)){
            return true;
        }
        getMessage().getMessageList().add(MessageType.DESTROYED.getMessage());
        return false;
    }
    public boolean requiredNinjasQuantity(Player player){
        if(MovementValidator.requiredNinjasQuantity(player)){
            return true;
        }
        return false;
    }
    public boolean lessThanRequiredNinjasQuantity(Player player){
        if(MovementValidator.lessThanRequiredNinjasQuantity(player)){
            return true;
        }
        return false;
    }

    private boolean attackAllowed(){

        return true;
    }

    public void attack(){

    }
}
