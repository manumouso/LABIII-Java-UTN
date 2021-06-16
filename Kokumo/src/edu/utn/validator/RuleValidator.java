package edu.utn.validator;

import edu.utn.manager.GameConstants;
import edu.utn.model.Board;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;
import edu.utn.enums.SquareType;


public class RuleValidator {


    public static boolean withinLimitsBoard(int i,int j){

        return (i>=0 && i<GameConstants.MAX_ROW) && (j>=0 && j<GameConstants.MAX_COLUMN);
    }

    public static boolean squareDestroyed(int i, int j){

        return Board.getInstance().getSquares()[i][j].name().equals(SquareType.DESTROYED.getName());
    }

    public static boolean squareOccupied(int i, int j){

        return Board.getInstance().getSquares()[i][j].hasNinja();
    }

    public static boolean ninjaDead(Player player,int i, int j){
        boolean dead= false;
        for(Ninja ninja: player.getNinjas()){
            if(ninja.getNinjaPosition().getI()==i && ninja.getNinjaPosition().getJ()==j){
                dead= ninja.isDead();
            }
        }
        return dead;
    }

    public static boolean commanderDead(Player player){

        return player.getNinjas().get(0).isDead();
    }

    public static boolean requiredNinjasQuantity(Player player){

        return player.getNinjas().size()==GameConstants.MAX_NINJAS;
    }

    public static boolean lessThanRequiredNinjasQuantity(Player player){

        return player.getNinjas().size()<GameConstants.MAX_NINJAS;
    }

    public static boolean canMoveThisTurn(Ninja ninja){
        return ninja.getAttackCounter() == 0 && ninja.getMovementCounter() == 0;
    }
}
