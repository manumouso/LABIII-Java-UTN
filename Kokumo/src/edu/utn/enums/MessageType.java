package edu.utn.enums;

public enum MessageType {

    OUTBOUNDARY(-1,"Invalid move, trying to leave the board"),DESTROYED(-2,"That square was destroyed, you can't position this ninja there"),OCCUPIED(-3,"That square is occupied by an allied ninja, you can't place this ninja there"),
    DEAD(-4,"Dead ninjas can't move"),CONSECUTIVE(-5,"Trying a consecutive move, this ninja must attack"),CREATE(-6,"You can't create your ninja outside the board"),EMPTY(-7,"The ninja is on an empty square"),TRAP(-8,"Oh you stepped on a trap, your ninja loses life points"),
    STONE(-9,"The ninja takes refuge in the stone and gains life points"),TREE(-10,"The ninja climbs the tree and gains extra points of attack");

    private int messageNumber;
    private String message;

    MessageType(int messageNumber, String message) {
        this.messageNumber = messageNumber;
        this.message = message;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getMessage() {
        return message;
    }
}
