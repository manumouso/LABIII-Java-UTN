package edu.utn.model.ninja;

import edu.utn.model.Player;
import javafx.scene.paint.Paint;

public interface Attack {

    void ninjaAttack(Player player);
    String name();
    Paint color();
}
