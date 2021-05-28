package edu.utn.entity.ninja;

import edu.utn.entity.Board;

public class NinjaPosition {
    private int i;
    private int j;

    public NinjaPosition(int i, int j) {
        this.i = i;
        this.j = j;
        Board.getInstance().getSquares()[this.i][this.j].setHasNinja(true);
    }
    //sobrecarga porque sino tenia un bug, cuando queria mover al ninja
    //lo movia usando el constructor de arriba para crear una nueva pos
    //pero por como venia la ejecucion detectaba como ocupado el casillero
    //entonces esta solucion:
    public NinjaPosition(int i, int j,boolean movementFlag) {
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
