package edu.utn.enums;

public enum MessageType {

    OUTBOUNDARY("Invalid move, trying to leave the board"),DESTROYED("That square was destroyed, you can't position this ninja there"),OCCUPIED("That square is occupied by an allied ninja, you can't place this ninja there"),
    DEAD("Dead ninjas can't move"),CONSECUTIVE("Trying a consecutive move, this ninja must attack"),CREATE("You can't create your ninja outside the board"),EMPTY("The ninja is on an empty square"),TRAP("Oh you stepped on a trap, your ninja loses life points"),
    STONE("The ninja takes refuge in the stone and gains life points"),TREE("The ninja climbs the tree and gains extra points of attack"),DEADCOMMANDER("Commander's dead, no ninja can move");

    private String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
