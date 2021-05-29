package edu.utn.entity.square;


import edu.utn.entity.ninja.Ninja;


public interface Square {

   void ninjaStandsOn(Ninja ninja);
   String name();
   boolean hasNinja();
   void setHasNinja(boolean hasNinja);

}
