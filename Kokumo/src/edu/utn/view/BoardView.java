package edu.utn.view;


import edu.utn.entity.AttackBoard;
import edu.utn.entity.Board;


public class BoardView {

    private Board board;
    private AttackBoard attackBoard;

    public BoardView(Board board, AttackBoard attackBoard) {
        this.board = board;
        this.attackBoard = attackBoard;
    }

    public Board getBoard() {
        return board;
    }

    public AttackBoard getAttackBoard() {
        return attackBoard;
    }

    public void printBoard(int pos, Board board){

        if(pos==25){
            return;
        }
        for(int i=pos; i< pos+5;i++){

            System.out.print(board.squares.get(i).name()+" ");
        }
        System.out.println();
        printBoard(pos+5, board);
    }
}
