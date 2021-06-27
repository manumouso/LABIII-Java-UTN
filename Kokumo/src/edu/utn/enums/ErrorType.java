package edu.utn.enums;

public enum ErrorType {
    server(-1,"Exception occurred when creating server: "),client(-2,"Exception occurred when creating client: "),
    join(-3,"Exception occurred trying to join the server: "),invite(-4,"Exception occurred trying to invite the client: "),
    moveClient(-5,"Exception occurred when checking if client ninjas can move: "),
    attackClient(-6,"Exception occurred when checking if client ninjas can attack: "),
    validDirection(-7,"Exception occurred trying to validate the client direction: "),
    sendAttack(-8,"Exception occurred when sending the attack: "),
    endTurn(-9,"Exception occurred ending turn: "),startConnectionIO(-10,"IO Exception when starting the connection: "),
    startConnectionEx(-11,"Exception occurred when starting the connection: "),
    closeConnectionIO(-12,"IO Exception happened closing the connection: "),
    closeConnectionEx(-13,"Exception happened closing the connection: "),validIP(-14,"Exception occurred validating the IP"),
    validPort(-15,"Exception occurred when validating port: "),
    checkingReceived(-16,"Exception occured when checking received messages: ");

    private int errorCode;
    private String errorMessage;

    ErrorType(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
