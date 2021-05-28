package edu.utn.view;

import edu.utn.entity.AttackBoard;
import edu.utn.entity.Board;
import edu.utn.entity.Size;
import edu.utn.entity.ninja.Attack;
import edu.utn.entity.ninja.Direction;
import edu.utn.entity.ninja.NinjaCommander;
import edu.utn.entity.ninja.NinjaPosition;


public class BoardView {
    NinjaCommander ninjaCommander= new NinjaCommander("commander", 2, new NinjaPosition(2, 2), new Attack() {
        @Override
        public void ninjaAttack() {
            System.out.println("Te ataque");
        }

        @Override
        public String name() {
            return "Ataque del comandante";
        }
    });

    public void printBoard(){
        System.out.println("\n\t\t\t\tTABLERO NINJA\n");
        for(int i=0;i< Size.MAX_ROW;i++){
            System.out.println("\t\t    ======================================================================");
            for(int j=0; j< Size.MAX_COLUMN;j++){
                System.out.print("\t\t     "+Board.getInstance().getSquares()[i][j].name());
                if(Board.getInstance().getSquares()[i][j].hasNinja()){
                    System.out.print(" "+ninjaCommander.getName());
                }
            }
            System.out.print("\n");
        }
        System.out.println("\t\t    ======================================================================");
        System.out.println("\n");
    }

    public void printAttackBoard(){
        System.out.println("\n\t\t\t\tTABLERO DE ATAQUES\n");
        for(int i=0;i< Size.MAX_ROW;i++){
            System.out.println("\t\t    ======================================================================");
            for(int j=0; j< Size.MAX_COLUMN;j++){
                System.out.print("\t\t     "+AttackBoard.getInstance().getSquares()[i][j].name());

            }
            System.out.print("\n");
        }
        System.out.println("\t\t    ======================================================================");
        System.out.println("\n");
    }

    public void advanceTest(){
        ninjaCommander.setDirection(Direction.getSouthWest());
        ninjaCommander.move();
        printBoard();
    }

}
