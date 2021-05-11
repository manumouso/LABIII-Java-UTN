package edu.utn.model.square;

import edu.utn.model.Player;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Tree extends Land {

    public Tree(int x, int y) {
        super(x, y);
    }

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Arbol";
    }

    @Override
    public Paint color() {
        return Color.GREEN;
    }

}
