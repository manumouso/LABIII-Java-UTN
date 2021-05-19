package edu.utn.entity.square;


import edu.utn.entity.Player;

public interface Square {

   void ninjaStandsOn(Player player);
   String name();
   int getX();
   int getY();
}
