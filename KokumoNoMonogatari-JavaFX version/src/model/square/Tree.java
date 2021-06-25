package model.square;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Player;

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
