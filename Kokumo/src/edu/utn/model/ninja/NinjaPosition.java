package edu.utn.model.ninja;


public class NinjaPosition {
    private int i;
    private int j;

    public NinjaPosition(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public NinjaPosition next(Direction direction) {
        return direction.nextPosition(this);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
