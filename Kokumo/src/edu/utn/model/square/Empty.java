package edu.utn.model.square;



import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;


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
