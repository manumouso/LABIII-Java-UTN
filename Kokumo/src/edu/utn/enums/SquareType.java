package edu.utn.enums;

public enum SquareType {

    DESTROYED("Destroyed"),EMPTY("Empty"),NINJA_TRAP("Trap"),STONE("Stone"),TREE("Tree");

    private String name;

    SquareType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
