package edu.utn.model.square;


import edu.utn.model.ninja.Ninja;


public interface Square {

   void ninjaStandsOn(Ninja ninja);
   String name();
   boolean hasNinja();
   void setHasNinja(boolean hasNinja);

}
