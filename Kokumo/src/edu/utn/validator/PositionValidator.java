package edu.utn.validator;

import edu.utn.entity.Board;
import edu.utn.entity.Size;
import edu.utn.entity.ninja.NinjaPosition;

public class PositionValidator {


    public static boolean validPosition(NinjaPosition position){

        return (position.getI()>=0 && position.getI()<Size.MAX_ROW) && (position.getJ()>=0 && position.getJ()<Size.MAX_COLUMN);
    }

    public static boolean isDestroyed(NinjaPosition position){
        int i= position.getI();
        int j= position.getJ();
        if (Board.getInstance().getSquares()[i][j].name().equals("Destroyed")){
            Board.getInstance().getMessages().getErrorMap().put(-1,"That square was destroyed, you can't position yourself there");
            return true;
        }else{
            return false;
        }
    }

    public static boolean isOccupied(NinjaPosition position){
        int i= position.getI();
        int j= position.getJ();
        if(Board.getInstance().getSquares()[i][j].hasNinja()){
            Board.getInstance().getMessages().getErrorMap().put(-2,"That square is occupied by an allied ninja, you can't position yourself there");
            return true;
        }else{
            return false;
        }
    }

    //estas dos cuando capture/genere ramdom la primera pos, y los ninja no salgan del tablero/se pisen
    public static boolean validInput(int i, int j){

        return (i>=0 && i<Size.MAX_ROW) && (j>=0 && j<Size.MAX_COLUMN);

    }
    public static boolean occupiedInput(int i,int j){

        return Board.getInstance().getSquares()[i][j].hasNinja();
    }
}
