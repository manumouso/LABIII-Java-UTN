package edu.utn.error;

public enum ErrorType {
    object(100,"Property instance is null"),ip(101,"IP instance is null"),port(102,"port instance is null");

    private int notDefined;
    private String notDefinedMessage;

    ErrorType(int notDefined, String notDefinedMessage) {
        this.notDefined = notDefined;
        this.notDefinedMessage = notDefinedMessage;
    }

    public int getNotDefined() {
        return notDefined;
    }

    public String getNotDefinedMessage() {
        return notDefinedMessage;
    }
}
