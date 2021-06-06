package edu.utn.factory;

import edu.utn.manager.GameConstants;
import edu.utn.model.AttackBoard;
import edu.utn.model.Board;
import edu.utn.model.square.Empty;
import edu.utn.model.square.NinjaTrap;
import edu.utn.model.square.Stone;
import edu.utn.model.square.Tree;
import edu.utn.view.BoardView;

public class BoardFactory {

    public void generateBoard(){

        for(int i=0;i<GameConstants.MAX_ROW;i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                int squareNumber = (int)Math.floor(Math.random()*GameConstants.MAX_COLUMN+1);
                String function="addSquare"+squareNumber;
                switch (function) {
                    case "addSquare2" -> addSquare2(i, j);
                    case "addSquare3" -> addSquare3(i, j);
                    case "addSquare4" -> addSquare4(i, j);
                    default -> addSquare1(i, j);
                }
            }
        }

    }
    private void addSquare1(int a, int b){
        Board.getInstance().getSquares()[a][b]=new Empty();
    }
    private void addSquare2(int a, int b){
        Board.getInstance().getSquares()[a][b]=new Stone();
    }
    private void addSquare3(int a, int b){
        Board.getInstance().getSquares()[a][b]=new Tree();
    }
    private void addSquare4(int a, int b){
        Board.getInstance().getSquares()[a][b]=new NinjaTrap();
    }

    public void generateAttackBoard(){

        for(int i = 0; i< GameConstants.MAX_ROW; i++){
            for(int j=0;j<GameConstants.MAX_COLUMN;j++){
                AttackBoard.getInstance().getSquares()[i][j]=new Empty();
            }
        }
    }

    public BoardView createBoardView(){

        return new BoardView();
    }
}
