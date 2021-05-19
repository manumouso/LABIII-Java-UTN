package edu.utn.entity.square;

public abstract class Land implements Square {
    private int x;
    private int y;

    public Land(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


}
