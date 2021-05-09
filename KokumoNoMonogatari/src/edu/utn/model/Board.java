package edu.utn.model;

import edu.utn.model.square.*;

import java.util.ArrayList;
import java.util.List;

//SINGLETON
public class Board {
    private static Board boardInstance;
    private List<Square> squares;

    public List<Square> getSquares() {
        return squares;
    }

    private Board(){
        squares=new ArrayList<>();
        addRow1();
        addRow2();
        addRow3();
        addRow4();
        addRow5();

    }

    public static Board getInstance(){
        if(boardInstance==null){
            boardInstance = new Board();
        }
        return boardInstance;
    }

    private void addRow1(){
        squares.add(new Empty(0,0));
        squares.add(new Stone(0,1));
        squares.add(new Empty(0,2));
        squares.add(new Tree(0,3));
        squares.add(new Empty(0,4));
    }
    private void addRow2(){
        squares.add(new Tree(1,0));
        squares.add(new Stone(1,1));
        squares.add(new Empty(1,2));
        squares.add(new NinjaTrap(1,3));
        squares.add(new Empty(1,4));
    }
    private void addRow3(){
        squares.add(new Stone(2,0));
        squares.add(new Stone(2,1));
        squares.add(new Empty(2,2));
        squares.add(new Tree(2,3));
        squares.add(new Empty(2,4));
    }
    private void addRow4(){
        squares.add(new Empty(3,0));
        squares.add(new NinjaTrap(3,1));
        squares.add(new Tree(3,2));
        squares.add(new Tree(3,3));
        squares.add(new Empty(3,4));
    }
    private void addRow5(){
        squares.add(new NinjaTrap(4,0));
        squares.add(new Stone(4,1));
        squares.add(new Empty(4,2));
        squares.add(new Tree(4,3));
        squares.add(new NinjaTrap(4,4));
    }

}
