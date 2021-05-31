package edu.utn.model;

import edu.utn.model.square.*;
import edu.utn.message.Message;

//SINGLETON
public class Board implements Size {

    private static Board boardInstance;
    private Square[][] squares;
    private Message messages;

    public Square[][] getSquares() {
        return squares;
    }

    public Message getMessages() {
        if(messages==null){
            messages=new Message();
        }
        return messages;
    }

    private Board(){
        squares=new Square[MAX_ROW][MAX_COLUMN];
        generateBoard();

    }

    public static Board getInstance(){
        if(boardInstance ==null){
            boardInstance = new Board();
        }
        return boardInstance;
    }

    private void generateBoard(){

        for(int i=0;i<MAX_ROW;i++){
            for(int j=0;j<MAX_COLUMN;j++){
                int squareNumber = (int)Math.floor(Math.random()*MAX_COLUMN+1);
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
