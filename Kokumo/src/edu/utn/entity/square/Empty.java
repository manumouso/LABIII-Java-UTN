package edu.utn.entity.square;



import edu.utn.entity.Board;
import edu.utn.entity.ninja.Ninja;


public class Empty extends Land {

    @Override
    public void ninjaStandsOn(Ninja ninja) {
        Board.getInstance().getMessages().getMessageList().add("The ninja is on an empty square. Ninja name: "+ ninja.getName());

    }

    @Override
    public String name() {
        return "Empty";
    }


}
