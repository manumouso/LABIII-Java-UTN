package edu.utn.model;


import edu.utn.factory.BoardFactory;
import edu.utn.manager.GameConstants;
import edu.utn.model.square.*;

//SINGLETON
public class Board {

    private static Board boardInstance;
    private Square[][] squares;

    public Square[][] getSquares() {
        return squares;
    }

    private Board(){
        squares=new Square[GameConstants.MAX_ROW][GameConstants.MAX_COLUMN];
        BoardFactory boardFactory = new BoardFactory();
        boardFactory.generateBoard(squares);

    }
    public static Board getInstance(){
        if(boardInstance ==null){
            boardInstance = new Board();
        }
        return boardInstance;
    }



}
