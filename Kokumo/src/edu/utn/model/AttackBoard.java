package edu.utn.model;

import edu.utn.factory.BoardFactory;
import edu.utn.manager.GameConstants;
import edu.utn.model.square.Square;

//SINGLETON
public class AttackBoard{

    private static AttackBoard attackBoardInstance;
    private Square[][] squares;

    public Square[][] getSquares() {
        return squares;
    }

    private AttackBoard(){
        squares=new Square[GameConstants.MAX_ROW][GameConstants.MAX_COLUMN];
        BoardFactory boardFactory = new BoardFactory();
        boardFactory.generateAttackBoard();
    }

    public static AttackBoard getInstance(){
        if(attackBoardInstance==null){
            attackBoardInstance = new AttackBoard();
        }
        return attackBoardInstance;
    }

}
