package model;

public class Wait implements State {
    @Override
    public boolean movementAllowed() {
        return false;
    }
}
