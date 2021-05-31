package edu.utn.entity;

public class Wait implements State {
    @Override
    public boolean movementAllowed() {
        return false;
    }
}
