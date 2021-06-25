package model.square;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Player;

public class Empty extends Land {


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
