package edu.utn.model.square;

import edu.utn.model.Player;

public interface Square {

   void playerStandsOn(Player player);
   String name();
   void fill();
   int getX();
   int getY();
}
