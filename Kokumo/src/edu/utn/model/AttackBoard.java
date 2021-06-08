package edu.utn.model;


import edu.utn.manager.GameConstants;
import edu.utn.model.square.Empty;
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
        generateAttackBoard();
    }

    public static AttackBoard getInstance(){
        if(attackBoardInstance==null){
            attackBoardInstance = new AttackBoard();
        }
        return attackBoardInstance;
    }


    public void generateAttackBoard(){

        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                squares[i][j]=new Empty();
            }
        }
    }

}
