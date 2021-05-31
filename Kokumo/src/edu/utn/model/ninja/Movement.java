package edu.utn.model.ninja;

public interface Movement {

    int CONSECUTIVE_MOVEMENTS_ALLOWED=0;
    void setDirection(Direction direction);
    void move();

}
