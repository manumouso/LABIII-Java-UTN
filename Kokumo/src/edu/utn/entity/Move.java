package edu.utn.entity;

public class Move implements State {
    @Override
    public boolean movementAllowed() {
        return true;
    }
}
