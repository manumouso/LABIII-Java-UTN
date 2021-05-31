package edu.utn.entity.ninja;

public interface Movement {

    int CONSECUTIVE_MOVEMENTS_ALLOWED=0;
    void setDirection(Direction direction);
    void move();

}
