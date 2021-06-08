package edu.utn.model;


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
        generateBoard();

    }
    public static Board getInstance(){
        if(boardInstance ==null){
            boardInstance = new Board();
        }
        return boardInstance;
    }

    private void generateBoard(){

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                int squareNumber = (int)Math.floor(Math.random()*GameConstants.MAX_COLUMN+1);
                String function="addSquare"+squareNumber;
                switch (function) {
                    case "addSquare2" -> addSquare2(i, j);
                    case "addSquare3" -> addSquare3(i, j);
                    case "addSquare4" -> addSquare4(i, j);
                    default -> addSquare1(i, j);
                }
            }
        }

    }
    private void addSquare1(int a, int b){
        squares[a][b]=new Empty();
    }
    private void addSquare2(int a, int b){
        squares[a][b]=new Stone();
    }
    private void addSquare3(int a, int b){
        squares[a][b]=new Tree();
    }
    private void addSquare4(int a, int b){
        squares[a][b]=new NinjaTrap();
    }

}
