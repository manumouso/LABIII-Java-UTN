package edu.utn.manager;

import edu.utn.connection.client.Client;
import edu.utn.controller.AttackController;
import edu.utn.controller.MovementController;
import edu.utn.controller.NinjaController;
import edu.utn.enums.ErrorType;
import edu.utn.enums.MessageType;
import edu.utn.error.OperationError;
import edu.utn.factory.NinjaFactory;
import edu.utn.message.Message;
import edu.utn.model.AttackBoard;
import edu.utn.model.Player;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.RuleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleManager {

    private NinjaFactory ninjaFactory;
    private MovementController movementController;
    private AttackController attackController;
    private NinjaController ninjaController;
    private Map<String, Direction> directionsMap;
    private Message message;
    public int messageArrived;
    private List<NinjaPosition> attackPositions;

    private OperationError opError;

    public OperationError getOpError() {
        if(opError==null){
            opError= new OperationError();
        }
        return opError;
    }

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
    public synchronized AttackController getAttackController() {
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
    public synchronized Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }
    private synchronized void addAll(List<String> messages){

        getMessage().getMessageList().addAll(messages);
    }
    public synchronized int getMessageArrived() {
        return messageArrived;
    }
    public synchronized void setMessageArrived(int messageArrived) {
        this.messageArrived = messageArrived;
    }

    public List<NinjaPosition> getAttackPositions() {
        if(attackPositions==null){
            attackPositions=new ArrayList<>();
        }
        return attackPositions;
    }

    public void resetAttackPositions(){
        getAttackPositions().clear();
    }

    public Ninja createNinja(int i, int j, boolean commander, int m){
        Ninja ninja=null;
        try{
            if(withinLimits(i,j,true)){
                if(freeSquare(i,j)){
                    NinjaFactory ninjaFactory = getNinjaFactory();
                    ninja =ninjaFactory.createNinja(i,j,commander,m);
                    MovementController movementController = getMovementController();
                    movementController.ninjaStandsOn(ninja);
                    addAll(movementController.getStandOnMessages());
                    movementController.getStandOnMessages().clear();
                    return ninja;
                }
            }
        }catch (Exception e){
            getOpError().add(ErrorType.createNinja.getErrorCode(),ErrorType.createNinja.getErrorMessage()+e.getMessage());
        }finally {
            return ninja;
        }
    }
    public NinjaPosition createPosition(int i,int j){
        NinjaPosition ninjaPosition= null;
        try{
            NinjaFactory ninjaFactory=getNinjaFactory();
            ninjaPosition= ninjaFactory.createPosition(i,j);
        }catch (Exception e){
            getOpError().add(ErrorType.createPosition.getErrorCode(),ErrorType.createPosition.getErrorMessage()+e.getMessage());
        }finally {
            return ninjaPosition;
        }

    }

    public boolean move(Ninja ninja, Direction direction,ServiceManager manager){
        boolean success=false;
        try{
            MovementController movementController = getMovementController();
            NinjaController ninjaController = getNinjaController();
            ninjaController.setDirection(ninja,direction);
            NinjaPosition current = ninjaController.getCurrentPosition(ninja);
            NinjaPosition next = movementController.getNextPosition(ninja);

            if(withinLimits(next.getI(), next.getJ())){
                if (squarePassable(next.getI(), next.getJ())){
                    if (freeSquare(next.getI(), next.getJ())){

                        movementController.move(ninja, current, next,manager);
                        addAll(movementController.getStandOnMessages());
                        movementController.getStandOnMessages().clear();
                        success= true;
                    }
                }
            }
        }catch (Exception e){
            getOpError().add(ErrorType.moveNinja.getErrorCode(),ErrorType.moveNinja.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }

    }

    public boolean commanderAlive(Player player){
        boolean success= false;
        try{
            if(!RuleValidator.commanderDead(player)){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.DEADCOMMANDER.getMessage());
                success=false;
            }

        }catch (Exception e){
            getOpError().add(ErrorType.deadCommander.getErrorCode(),ErrorType.deadCommander.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }

    }
    private boolean withinLimits(int i,int j,boolean create){
        boolean success= false;
        try{
            if(RuleValidator.withinLimitsBoard(i,j)){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.CREATE.getMessage());
                success= false;
            }

        }catch (Exception e){
            getOpError().add(ErrorType.withinLimits.getErrorCode(),ErrorType.withinLimits.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }

    }
    private boolean withinLimits(int i,int j){
        boolean success= false;
        try{
            if(RuleValidator.withinLimitsBoard(i,j)){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.OUTBOUNDARY.getMessage());
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.withinLimits.getErrorCode(),ErrorType.withinLimits.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }
    private boolean freeSquare(int i,int j){
        boolean success= false;
        try{
            if(!RuleValidator.squareOccupied(i,j)){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.OCCUPIED.getMessage());
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.freeSquare.getErrorCode(),ErrorType.freeSquare.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }
    public boolean isAlive(Ninja ninja){
        boolean success= false;
        try{
            if(!ninja.isDead()){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.DEAD.getMessage());
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.isAlive.getErrorCode(),ErrorType.isAlive.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }
    public boolean movementAllowed(Ninja ninja){
        boolean success= false;
        try{
            if(ninja.isMovedPreviousTurn()){
                getMessage().getMessageList().add(MessageType.CONSECUTIVE.getMessage());
                success= false;
            }else{
                success= true;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.movementAllowed.getErrorCode(),ErrorType.movementAllowed.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }


    }
    private boolean squarePassable(int i, int j){
        boolean success= false;
        try{
            if(!RuleValidator.squareDestroyed(i, j)){
                success= true;
            }else{
                getMessage().getMessageList().add(MessageType.DESTROYED.getMessage());
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.squarePassable.getErrorCode(),ErrorType.squarePassable.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }


    }
    public boolean requiredNinjasQuantity(Player player){
        boolean success= false;
        try{
            if(RuleValidator.requiredNinjasQuantity(player)){
                success= true;
            }else{
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.requiredQuantity.getErrorCode(),ErrorType.requiredQuantity.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }
    public boolean lessThanRequiredNinjasQuantity(Player player){
        boolean success= false;
        try{
            if(RuleValidator.lessThanRequiredNinjasQuantity(player)){
                success= true;
            }else{
                success= false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.lessQuantity.getErrorCode(),ErrorType.lessQuantity.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }


    }
    public boolean canMoveThisTurn(Ninja ninja){
        boolean success= false;
        try{
            if(RuleValidator.canMoveThisTurn(ninja)){
                success=true;
            }else{
                success=false;
            }
        }catch (Exception e){
            getOpError().add(ErrorType.lessQuantity.getErrorCode(),ErrorType.lessQuantity.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }
    }

    public synchronized String attackReceived(Player player, NinjaPosition attackPosition,int attackPoints){
        String message=" ";
        try{
            if(attackAllowed(player,attackPosition)){
                message = getAttackController().attackReceived(player, attackPosition,attackPoints);

                addAll(getAttackController().getAttackMessages());
                getAttackController().getAttackMessages().clear();
                setMessageArrived(getMessageArrived()+1);

            }else{
                message="WRONG ATTACK!!! Try again";
            }
        }catch (Exception e){
            getOpError().add(ErrorType.attackReceived.getErrorCode(),ErrorType.attackReceived.getErrorMessage()+e.getMessage());
        }finally {
            return message;
        }
    }

    private boolean attackAllowed(Player player,NinjaPosition attackPosition){
        boolean success= false;
        try{
            int i= attackPosition.getI();
            int j= attackPosition.getJ();
            if(RuleValidator.withinLimitsBoard(i,j)){
                if(!RuleValidator.squareDestroyed(i,j)){
                    success= !RuleValidator.ninjaDead(player, i, j);
                }
            }
        }catch (Exception e){
            getOpError().add(ErrorType.validatingAttack.getErrorCode(),ErrorType.validatingAttack.getErrorMessage()+e.getMessage());
        }finally {
            return success;
        }

    }

    public synchronized void ninjaDiedByTrap(){
        getMessage().getMessageList().add("Enemy Ninja died standing on a trap");
        setMessageArrived(getMessageArrived()+1);
    }

    public boolean choseRepeatedPosition(NinjaPosition attack){
        for(NinjaPosition position:getAttackPositions()){
            if(attack.getI()== position.getI() && attack.getJ()== position.getJ()){
                return true;
            }
        }
        return false;
    }

    public synchronized String canMoveClient(boolean commanderDead,int attackCounter,int moveCounter, boolean movedPreviousTurn,boolean ninjaDead){
        String message=" ";
        try{
            if(!RuleValidator.ninjaDead(commanderDead)){
                if(!RuleValidator.ninjaDead(ninjaDead)){
                    if(RuleValidator.canMoveThisTurn(attackCounter,moveCounter)){
                        if(!RuleValidator.movedPreviousTurn(movedPreviousTurn)){
                            message= MessageType.ALLOWED.getMessage();
                        }else{
                            message= MessageType.CONSECUTIVE.getMessage();
                        }
                    }else{
                        message= MessageType.ALREADY.getMessage();
                    }
                }else{
                    message= MessageType.DEAD.getMessage();
                }
            }else{
                message= MessageType.DEADCOMMANDER.getMessage();
            }
        }catch (Exception e){
            getOpError().add(ErrorType.canMoveClient.getErrorCode(),ErrorType.canMoveClient.getErrorMessage()+e.getMessage());
        }finally {
            return message;
        }

    }

    public synchronized String canAttackClient(int attackCounter, int moveCounter, boolean ninjaDead) {
        String message=" ";
        try{
            if (!RuleValidator.ninjaDead(ninjaDead)) {
                if (RuleValidator.canMoveThisTurn(attackCounter, moveCounter)) {
                    message= MessageType.ATTACK_ALLOWED.getMessage();
                } else {
                    message= MessageType.ALREADY.getMessage();
                }
            }else{
                message= MessageType.DEAD.getMessage();
            }
        }catch (Exception e){
            getOpError().add(ErrorType.canAttackClient.getErrorCode(),ErrorType.canAttackClient.getErrorMessage()+e.getMessage());
        }finally {
            return message;
        }

    }


    public synchronized String validDirectionClient(int nextI,int nextJ,NinjaPosition pos1, NinjaPosition pos2,NinjaPosition pos3){
        String message=" ";
        try{
            if(RuleValidator.withinLimitsBoard(nextI,nextJ)){
                if(!RuleValidator.squareDestroyed(AttackBoard.getInstance().getSquares()[nextI][nextJ].name())){
                    NinjaPosition next = new NinjaPosition(nextI,nextJ);
                    if(!RuleValidator.squareOccupied(next,pos1,pos2,pos3)){
                        message= MessageType.VALID_DIRECTION.getMessage();
                    }else{
                        message= MessageType.OCCUPIED.getMessage();
                    }
                }else {
                    message= MessageType.DESTROYED.getMessage();
                }
            }else{
                message= MessageType.OUTBOUNDARY.getMessage();
            }
        }catch (Exception e){
            getOpError().add(ErrorType.validDirectionClient.getErrorCode(),ErrorType.validDirectionClient.getErrorMessage()+e.getMessage());
        }finally {
            return message;
        }


    }

    public synchronized void moveClient(Ninja ninja, Direction direction, ServiceManager manager) {
        try{
            MovementController movementController = getMovementController();
            NinjaController ninjaController = getNinjaController();
            ninjaController.setDirection(ninja, direction);
            NinjaPosition current = ninjaController.getCurrentPosition(ninja);
            NinjaPosition next = movementController.getNextPosition(ninja);
            movementController.move(ninja, current, next, manager);
            addAll(movementController.getStandOnMessages());
            movementController.getStandOnMessages().clear();
        }catch (Exception e){
            getOpError().add(ErrorType.validDirectionClient.getErrorCode(),ErrorType.validDirectionClient.getErrorMessage()+e.getMessage());
        }

    }
}
