package edu.utn.view;

import edu.utn.entity.AttackBoard;
import edu.utn.entity.Board;
import edu.utn.entity.Size;
import edu.utn.entity.ninja.*;


public class BoardView {


    Ninja ninja= new NinjaCommander("NC", 2, 1,new NinjaPosition(2, 2), new Attack() {
        @Override
        public void ninjaAttack() {
            System.out.println("I attacked");
        }

        @Override
        public String name() {
            return "Ninja commander special move";
        }
    });

    public void printBoard(){
        System.out.println("\n\t\t\t\tNINJA BOARD\n");
        for(int i=0;i< Size.MAX_ROW;i++){
            System.out.println("\t\t================================================================================");
            for(int j=0; j< Size.MAX_COLUMN;j++){
                if(Board.getInstance().getSquares()[i][j].hasNinja()){
                    hasNinja(i,j);
                }else{
                    noNinja(i,j);
                }
            }
            System.out.print("\n");
        }
        System.out.println("\t\t================================================================================");
        System.out.println("\n");
    }
    public void printAttackBoard(){
        System.out.println("\n\t\t\t\tATTACK BOARD\n");
        for(int i=0;i< Size.MAX_ROW;i++){
            System.out.println("\t\t================================================================================");
            for(int j=0; j< Size.MAX_COLUMN;j++){
                System.out.print("\t\t     "+AttackBoard.getInstance().getSquares()[i][j].name());
            }
            System.out.print("\n");
        }
        System.out.println("\t\t================================================================================");
        System.out.println("\n");
    }

    public void printMessages(){
        for(String message:Board.getInstance().getMessages()){
            System.out.println("\t\t     "+message);
        }
        System.out.println("\n");
    }

    public void advanceTest(){
        ninja.setDirection(Direction.getSouthWest());
        ninja.move();
        printBoard();

    }
    public void advanceTest2(){
        ninja.setDirection(Direction.getSouthWest());
        ninja.move();
        printBoard();
    }
    public void advanceTest3(){
        ninja.setDirection(Direction.getNorth());
        ninja.move();
        printBoard();
    }

    private void hasNinja(int i,int j){

        if(Board.getInstance().getSquares()[i][j].name().equals("Trap") || Board.getInstance().getSquares()[i][j].name().equals("Empty")) {
            System.out.print("\t\t     **"+" "+ninja.getName());
        }else{
            System.out.print("\t\t  "+Board.getInstance().getSquares()[i][j].name()+" "+ninja.getName());
        }

    }

    private void noNinja(int i, int j){
        if(Board.getInstance().getSquares()[i][j].name().equals("Trap") || Board.getInstance().getSquares()[i][j].name().equals("Empty")) {
            System.out.print("\t\t     *****");
        }else{
            System.out.print("\t\t     "+Board.getInstance().getSquares()[i][j].name());

        }
    }

}
