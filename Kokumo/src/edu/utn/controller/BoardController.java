package edu.utn.controller;


import edu.utn.factory.BoardFactory;
import edu.utn.model.AttackBoard;
import edu.utn.model.Board;


public class BoardController {

    public void clearBoards(boolean ninjasBoard){
        BoardFactory boardFactory = new BoardFactory();
        if(ninjasBoard){

            boardFactory.generateBoard(Board.getInstance().getSquares());

        }else{
            boardFactory.generateAttackBoard(AttackBoard.getInstance().getSquares());
        }
    }
}
