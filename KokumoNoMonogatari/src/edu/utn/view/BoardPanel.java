package edu.utn.view;

import edu.utn.model.Board;
import edu.utn.model.square.Square;

import javax.swing.*;
import java.awt.*;


public class BoardPanel extends JPanel {
    private String landName;
    private int xx;
    private int yy;

    public BoardPanel(String landName, int xx, int yy) {
        this.landName = landName;
        this.xx = xx;
        this.yy = yy;
    }

    @Override
    public void paintComponent(Graphics g){

        // clear the previous painting
        super.paintComponent(g);
        // cast Graphics to Graphics2D
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red); // sets Graphics2D color
        // draw the rectangle
        g2.drawRect(0,0,100,100); // drawRect(x-position, y-position, width, height)
        g2.setColor(Color.blue);
        //g2.fillRect(0,0,100,100); // fill new rectangle with color blue
        g2.drawString(landName,xx,yy);


    }



}
