package edu.utn.model.square;

import edu.utn.model.Player;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class NinjaTrap extends Land {


    public NinjaTrap(int x, int y) {
        super(x, y);
    }

    @Override
    public void playerStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Trampa";
    }

    @Override
    public Paint color() {
        return Color.WHITE;
    }


}
