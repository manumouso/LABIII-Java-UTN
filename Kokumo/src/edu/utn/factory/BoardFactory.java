package edu.utn.factory;

import edu.utn.manager.GameConstants;
import edu.utn.model.square.*;


public class BoardFactory {

    public void generateBoard(Square[][] squares){

        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                int squareNumber = (int)Math.floor(Math.random()*GameConstants.MAX_COLUMN+1);
                String function="addSquare"+squareNumber;
                switch (function) {
                    case "addSquare2":
                        squares[i][j]=new Stone();
                        break;
                    case "addSquare3":
                        squares[i][j]=new Tree();
                        break;
                    case "addSquare4":
                        squares[i][j]=new NinjaTrap();
                        break;
                    default:
                        squares[i][j]=new Empty();
                        break;
                }
            }
        }

    }
    public void generateAttackBoard(Square[][] squares){

        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                squares[i][j]=new Empty();
            }
        }
    }
}
