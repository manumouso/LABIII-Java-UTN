package edu.utn.manager;

import edu.utn.controller.BoardController;
import edu.utn.controller.MovementController;
import edu.utn.factory.MenuFactory;
import edu.utn.factory.NinjaFactory;
import edu.utn.message.Message;
import edu.utn.enums.MessageType;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.validator.MovementValidator;
import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

import java.util.Map;

public class GameManager {

    private Message message;

    public Message getMessage() {
        if(message==null){
            message=new Message();
        }
        return message;
    }
    private void putAll(Map<Integer,String> messageMap){

        getMessage().getMessageMap().putAll(messageMap);
    }
    public void startGame(){
        MenuFactory menuFactory= new MenuFactory();
        Introduction intro = menuFactory.createIntro();
        intro.print();
        PrimaryStage primaryStage = menuFactory.createPrimaryStage();
        primaryStage.menu();
    }
    public void clearBoards(){
        BoardController boardController = new BoardController();
        boardController.clearBoards();
    }

    public void createNinja(String name,int i,int j){
        if(MovementValidator.withinLimitsBoard(i,j)){
            if(!MovementValidator.squareOccupied(i,j)){
                NinjaFactory ninjaFactory = new NinjaFactory();
                Ninja ninja =ninjaFactory.createNinja(name,i,j);
                MovementController movementController = new MovementController();
                movementController.ninjaStandsOn(ninja);
                putAll(movementController.getMessage());
            }else{
                getMessage().getMessageMap().put(MessageType.OCCUPIED.getMessageNumber(), MessageType.OCCUPIED.getMessage());
            }
        }else{
            getMessage().getMessageMap().put(MessageType.CREATE.getMessageNumber(), MessageType.CREATE.getMessage());
        }
    }

    public void move(Ninja ninja, Direction direction){
        try{
            if(!ninja.isDead()){
                if(!MovementValidator.movedPreviousTurn(ninja.getMovementCounter())){
                    ninja.setDirection(direction);
                    MovementController movementController = new MovementController();
                    NinjaPosition current = ninja.getNinjaPosition();
                    NinjaPosition next = movementController.calculateNextPosition(ninja);

                    if(MovementValidator.withinLimitsBoard(next.getI(), next.getJ())){
                        if(!MovementValidator.squareDestroyed(next.getI(), next.getJ())){
                            if(!MovementValidator.squareOccupied(next.getI(), next.getJ())){

                                movementController.move(ninja,current,next);
                                putAll(movementController.getMessage());

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
