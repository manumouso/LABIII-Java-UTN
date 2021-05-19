package edu.utn.entity;

import edu.utn.entity.square.Empty;

public class AttackBoard extends Board{

    @Override
    public void addRow1() {
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));

    }
    @Override
    public void addRow2() {
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));

    }
    @Override
    public void addRow3() {
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));

    }
    @Override
    public void addRow4() {
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));

    }
    @Override
    public void addRow5() {
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
        squares.add(new Empty(0,0));
    }


}
