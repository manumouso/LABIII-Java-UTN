package edu.utn.enums;

public enum NetworkType {
    IP("Invalid IP"),PORT("Invalid PORT"),SERVER("Server created correctly"),CLIENT("Client created correctly"),
    RUNNING("Running");

    private String message;

    NetworkType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
