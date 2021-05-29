package edu.utn.view;

import edu.utn.entity.AttackBoard;
import edu.utn.entity.Board;
import edu.utn.entity.Player;
import edu.utn.entity.Size;
import edu.utn.entity.ninja.*;



public class BoardView {

    //A LAS FUNCIONES QUE IMPRIMEN LOS TABLEROS LES TENGO QUE inyectar los ninja de alguna manera
    //tal vez validiendome de la funcion has ninja, tomo esos i,j, comparo contra la lista de ninjas del jugador
    //aproximacion abajo
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

    public void printBoard(/*Player player*/){
        System.out.println("\n\t\t\t\tNINJA BOARD\n");
        for(int i=0;i< Size.MAX_ROW;i++){
            System.out.println("\t\t================================================================================");
            for(int j=0; j< Size.MAX_COLUMN;j++){
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
        for(String message:Board.getInstance().getMessages().getMessageList()){
            System.out.println("\t\t     "+message);
        }
        System.out.println("\n");
    }
    public void printMovementErrors(){
        for(String error: Board.getInstance().getMessages().getErrorMap().values()){
            System.out.println("\t\t     "+error);
        }
        System.out.println("\n");
    }

    public void cleanBoard(){
        for(int i=0;i<Size.MAX_ROW;i++){
            for(int j=0;j<Size.MAX_COLUMN;j++){
                Board.getInstance().getSquares()[i][j].setHasNinja(false);
            }
        }
        Board.getInstance().getMessages().getMessageList().clear();
        Board.getInstance().getMessages().getErrorMap().clear();
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
