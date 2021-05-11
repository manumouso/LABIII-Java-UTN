package edu.utn.model.square;

import edu.utn.model.Player;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Empty extends Land{


    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Vacio";
    }

    @Override
    public Paint color() {
        return Color.WHITE;
    }


}
