package edu.utn.controller;

import edu.utn.manager.GameConstants;
import edu.utn.model.AttackBoard;
import edu.utn.model.Board;
import edu.utn.model.square.Empty;

public class BoardController {

    public void clearBoards(){
        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                Board.getInstance().getSquares()[i][j].setHasNinja(false);
                AttackBoard.getInstance().getSquares()[i][j]=new Empty();
            }
        }
        Board.getInstance().getMessages().clear();
        AttackBoard.getInstance().getMessages().clear();
    }
}
