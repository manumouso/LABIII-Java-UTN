package edu.utn.model.ninja;

public class Direction {

    private int valueI;
    private int valueJ;

    public Direction(int valueI, int valueJ) {
        this.valueI = valueI;
        this.valueJ = valueJ;
    }

    private static Direction north;
    private static Direction northEast;
    private static Direction northWest;
    private static Direction south;
    private static Direction southEast;
    private static Direction southWest;
    private static Direction east;
    private static Direction west;

    static {
        north = new Direction(-1, 0);
        northEast= new Direction(-1,1);
        northWest= new Direction(-1,-1);
        south = new Direction(1, 0);
        southEast= new Direction(1,1);
        southWest=new Direction(1,-1);
        east = new Direction(0, 1);
        west = new Direction(0, -1);
    }

    public NinjaPosition nextPosition(NinjaPosition position) {
        int nextI = position.getI() + this.valueI;
        int nextJ = position.getJ() + this.valueJ;

        return new NinjaPosition(nextI, nextJ);
    }

    public static Direction getNorth() {
        return north;
    }

    public static Direction getNorthEast() {
        return northEast;
    }

    public static Direction getNorthWest() {
        return northWest;
    }

    public static Direction getSouth() {
        return south;
    }

    public static Direction getSouthEast() {
        return southEast;
    }

    public static Direction getSouthWest() {
        return southWest;
    }

    public static Direction getEast() {
        return east;
    }

    public static Direction getWest() {
        return west;
    }
}
