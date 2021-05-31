package edu.utn.model.square;


public abstract class Land implements Square {

    private boolean hasNinja;

    @Override
    public boolean hasNinja() {
        return hasNinja;
    }
    @Override
    public void setHasNinja(boolean hasNinja) {
        this.hasNinja = hasNinja;
    }
}
