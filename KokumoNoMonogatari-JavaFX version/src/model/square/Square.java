package model.square;

import javafx.scene.paint.Paint;
import model.Player;

public interface Square {

   void ninjaStandsOn(Player player);
   String name();
   Paint color();
   int getX();
   int getY();
}
