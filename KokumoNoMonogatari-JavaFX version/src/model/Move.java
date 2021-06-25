package model;

public class Move implements State {
    @Override
    public boolean movementAllowed() {
        return true;
    }
}
