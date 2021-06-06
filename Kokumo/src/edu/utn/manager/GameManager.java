package edu.utn.manager;

import edu.utn.controller.BoardController;
import edu.utn.controller.MovementController;
import edu.utn.factory.NinjaFactory;
import edu.utn.message.Message;
import edu.utn.message.MessageType;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.MovementValidator;

public class GameManager {

    private Message message;

    public Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }


    public void clearBoards(){
        BoardController boardController = new BoardController();
        boardController.clearBoards();
    }

    public void createNinja(String name,int i,int j){
        if(MovementValidator.withinLimitsBoard(i,j)){
            NinjaFactory ninjaFactory = new NinjaFactory();
            Ninja ninja =ninjaFactory.createNinja(name,i,j);
            MovementController movementController = new MovementController();
            movementController.ninjaStandsOn(ninja);
        }else{
            getMessage().getMessageMap().put(MessageType.CREATE.getMessageNumber(), MessageType.CREATE.getMessage());
        }


    }

    public void move(Ninja ninja, Direction direction){
        try{
            if(!ninja.isDead()){
                if(!MovementValidator.movedPreviousTurn(ninja.getMovementCounter())){

                    MovementController movementController = new MovementController();
                    NinjaPosition current = ninja.getNinjaPosition();
                    NinjaPosition next = movementController.calculateNextPosition(ninja);

                    if(MovementValidator.withinLimitsBoard(next.getI(), next.getJ())){
                        if(!MovementValidator.squareDestroyed(next.getI(), next.getJ())){
                            if(!MovementValidator.squareOccupied(next.getI(), next.getJ())){

                                movementController.move(ninja,current,next);

                            }else{
                                getMessage().getMessageMap().put(MessageType.OCCUPIED.getMessageNumber(), MessageType.OCCUPIED.getMessage());
                            }
                        }else{
                            getMessage().getMessageMap().put(MessageType.DESTROYED.getMessageNumber(), MessageType.DESTROYED.getMessage());
                        }
                    }else{
                        getMessage().getMessageMap().put(MessageType.OUTBOUNDARY.getMessageNumber(), MessageType.OUTBOUNDARY.getMessage());
                    }
                }else{
                    getMessage().getMessageMap().put(MessageType.CONSECUTIVE.getMessageNumber(),MessageType.CONSECUTIVE.getMessage());
                }
            }else{
                getMessage().getMessageMap().put(MessageType.DEAD.getMessageNumber(), MessageType.DEAD.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void attack(){

    }
}
