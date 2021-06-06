package edu.utn.validator;

import edu.utn.manager.GameConstants;
import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;
import edu.utn.enums.NinjaType;
import edu.utn.enums.SquareType;

import java.util.List;

public class MovementValidator {

    public static boolean ninjaCommanderAlive(List<Ninja> ninjas){
        int commander=0;
        for(Ninja ninja:ninjas){
            if(isCommander(ninja)){
                commander++;
            }
        }
        return commander==1;
    }

    public static boolean withinLimitsBoard(int i,int j){

        return (i>=0 && i<GameConstants.MAX_ROW) && (j>=0 && j<GameConstants.MAX_COLUMN);
    }

    public static boolean squareDestroyed(int i, int j){

        return Board.getInstance().getSquares()[i][j].name().equals(SquareType.DESTROYED.getName());
    }

    public static boolean squareOccupied(int i, int j){

        return Board.getInstance().getSquares()[i][j].hasNinja();
    }

    public static boolean movedPreviousTurn(int movementCounter){

        return movementCounter> GameConstants.CONSECUTIVE_MOVEMENTS_ALLOWED;
    }

    public static boolean isCommander(Ninja ninja){

        return ninja.getName().equals(NinjaType.COMMANDER.getName());
    }
}
