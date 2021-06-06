package edu.utn.manager;

import edu.utn.controller.BoardController;
import edu.utn.controller.MovementController;
import edu.utn.controller.NinjaController;
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

import java.util.List;

public class GameManager {

    private BoardController boardController;
    private NinjaFactory ninjaFactory;
    private MovementController movementController;
    private NinjaController ninjaController;
    private MenuFactory menuFactory;
    private Message message;


    private BoardController getBoardController() {
        if(boardController==null){
            boardController = new BoardController();
        }
        return boardController;
    }

    private NinjaFactory getNinjaFactory() {
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

    private NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController= new NinjaController();
        }
        return ninjaController;
    }

    private MenuFactory getMenuFactory() {
        if(menuFactory==null){
            menuFactory = new MenuFactory();
        }
        return menuFactory;
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

    public void startGame(){
        MenuFactory menuFactory= getMenuFactory();
        Introduction intro = menuFactory.createIntro();
        intro.print();
        PrimaryStage primaryStage = menuFactory.createPrimaryStage();
        primaryStage.menu(this);
    }

    public void clearBoards(){
        BoardController boardController = getBoardController();
        boardController.clearBoards();
    }

    public Ninja createNinja(int i,int j,boolean commander){
        if(withinLimits(i,j,true)){
            if(freeSquare(i,j)){
                NinjaFactory ninjaFactory = getNinjaFactory();
                Ninja ninja =ninjaFactory.createNinja(i,j,commander);
                MovementController movementController = getMovementController();
                movementController.ninjaStandsOn(ninja);
                addAll(movementController.getStandOnMessages());
                return ninja;
            }
        }

        return null;
    }

    public void move(Ninja ninja, Direction direction){

        if(isAlive(ninja)){
            if(movementAllowed(ninja)){
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
                        }
                    }
                }
            }
        }
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

    private boolean movementAllowed(Ninja ninja){
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

    public void attack(){

    }
}
