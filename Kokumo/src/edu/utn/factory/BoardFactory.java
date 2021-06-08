package edu.utn.factory;

import edu.utn.view.BoardPrinter;

public class BoardFactory {


    public BoardPrinter createBoardView(){

        return new BoardPrinter();
    }
}
