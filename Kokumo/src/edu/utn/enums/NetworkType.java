package edu.utn.enums;

public enum NetworkType {
    IP("Invalid IP"),PORT("Invalid PORT, must be greater than or equal 8000 and less than or equal to 65000"),SERVER("Server created correctly"),
    CLIENT("Client created correctly");

    private String message;

    NetworkType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
