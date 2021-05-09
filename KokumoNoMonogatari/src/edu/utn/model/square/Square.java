package edu.utn.model.square;

import edu.utn.model.Player;
import javafx.scene.paint.Paint;

public interface Square {

   void playerStandsOn(Player player);
   String name();
   Paint color();
   int getX();
   int getY();
}
