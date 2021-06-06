package edu.utn.view;

import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.AttackBoard;
import edu.utn.model.Board;
import edu.utn.model.ninja.*;



public class BoardView {

    //A LAS FUNCIONES QUE IMPRIMEN LOS TABLEROS LES TENGO QUE inyectar los ninja de alguna manera
    //tal vez validiendome de la funcion has ninja, tomo esos i,j, comparo contra la lista de ninjas del jugador
    //aproximacion abajo
    Ninja ninja= new NinjaCommander("NC", 2, 1,new NinjaPosition(2, 2));

    public void printBoard(/*Player player*/){
        System.out.println("\n\t\t\t\tNINJA BOARD\n");
        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            System.out.println("\t\t================================================================================");
            for(int j=0; j< GameConstants.MAX_COLUMN;j++){
                if(Board.getInstance().getSquares()[i][j].hasNinja()){
                    hasNinja(i,j/*,player*/);
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
        for(int i=0;i< GameConstants.MAX_ROW;i++){
            System.out.println("\t\t================================================================================");
            for(int j=0; j< GameConstants.MAX_COLUMN;j++){
                System.out.print("\t\t     "+AttackBoard.getInstance().getSquares()[i][j].name());
            }
            System.out.print("\n");
        }
        System.out.println("\t\t================================================================================");
        System.out.println("\n");
    }

    public void printMessages(GameManager gameManager){
        for(String error: gameManager.getMessage().getMessageMap().values()){
            System.out.println("\t\t     "+error);
        }
        System.out.println("\n");
    }

    public void clearBoards(GameManager gameManager){
        gameManager.clearBoards();
    }

    private void hasNinja(int i,int j/*,Player player*/){
        //Esta puede que sea la solucion cuando saque al ninja violentamente generado en esta clase
        //a testear despues, la magia del puntero ? :D
//        Ninja ninja;
//        for(Ninja nin: player.getNinjas()){
//            if(nin.getNinjaPosition().getI()==i && nin.getNinjaPosition().getJ()==j){
//                ninja=nin;
//            }
//        }

        if(isTrap(i,j)) {
            System.out.print("\t\t     **" + " " + ninja.getName());
        }else if(isEmpty(i,j)){
            System.out.print("\t\t       " + " " + ninja.getName());
        }else{
            System.out.print("\t\t  "+Board.getInstance().getSquares()[i][j].name()+" "+ninja.getName());
        }

    }

    private void noNinja(int i, int j){
        if(isTrap(i,j) || isEmpty(i,j)) {
            System.out.print("\t\t     *****");
        }else{
            System.out.print("\t\t     "+Board.getInstance().getSquares()[i][j].name());

        }
    }

    private boolean isTrap(int i, int j){

        return Board.getInstance().getSquares()[i][j].name().equals("Trap");
    }

    private boolean isEmpty(int i, int j){

        return Board.getInstance().getSquares()[i][j].name().equals("Empty");
    }

//estas pruebas vuelan
//    public void advanceTest(){
//        ninja.setDirection(Direction.getSouthWest());
//        ninja.move();
//        printBoard();
//
//    }
//    public void advanceTest2(){
//        ninja.setDirection(Direction.getSouthWest());
//        ninja.move();
//        printBoard();
//    }
//    public void advanceTest3(){
//        ninja.setDirection(Direction.getNorth());
//        ninja.move();
//        printBoard();
//    }
//    public void advanceTest4(){
//        ninja.setDirection(Direction.getWest());
//        ninja.move();
//        printBoard();
//    }
//
//    public void advanceTest5(){
//        ninja.setDirection(Direction.getEast());
//        ninja.move();
//        printBoard();
//    }
//
//    public void advanceTest6(){
//        ninja.setDirection(Direction.getEast());
//        ninja.move();
//        printBoard();
//    }

}
