package model.ninja;

import javafx.scene.paint.Paint;
import model.Player;

public interface Attack {

    void ninjaAttack(Player player);
    String name();
    Paint color();
}
