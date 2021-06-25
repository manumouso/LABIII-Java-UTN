package model.square;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Player;

public class Stone extends Land {


    public Stone(int x, int y) {
        super(x, y);
    }

    @Override
    public void ninjaStandsOn(Player player) {

    }

    @Override
    public String name() {
        return "Piedra";
    }

    @Override
    public Paint color() {
        return Color.BLACK;
    }

}
