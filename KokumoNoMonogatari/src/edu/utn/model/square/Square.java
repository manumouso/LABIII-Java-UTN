package edu.utn.model.square;

import edu.utn.model.Player;

public interface Square {

   void action(Player player);
   String name();
   void fill();
}
