package edu.utn.entity.ninja;

import edu.utn.entity.Board;

public class NinjaPosition {
    private int i;
    private int j;

    public NinjaPosition(int i, int j) {
        this.i = i;
        this.j = j;
        //chequear que la pos no este ocupada cuando cree a los otros ninja!!!
        Board.getInstance().getSquares()[this.i][this.j].setHasNinja(true);
    }
    //this overload fix the bug that happened when i tried to check if de next position of the ninja
    //is destroyed or occupied in the Ninja.move() function, and the ninja hasnt positionated there yet
    //but it appeared as if the square was occupied by how the first constructor created the new position
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
