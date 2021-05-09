package edu.utn.view;

import edu.utn.model.Board;
import edu.utn.model.square.Square;

import javax.swing.*;
import java.awt.*;

public class BoardView {
    JFrame jFrame;


    public BoardView(){
        jFrame = new JFrame();
        // get the content area of Panel.
        Container container = jFrame.getContentPane();
        // set the LayoutManager
        container.setLayout(new GridLayout(5,5));

        // add MyPanel object into container
        for(Square square: Board.getInstance().getSquares()){

            container.add(new BoardPanel(square.name(),square.getX()+10,square.getY()+30));


        }

        // set the size of the JFrame
        jFrame.setSize(550,550);
        // make the JFrame visible
        jFrame.setVisible(true);
        // sets close behavior; EXIT_ON_CLOSE invokes System.exit(0) on closing the JFrame
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
