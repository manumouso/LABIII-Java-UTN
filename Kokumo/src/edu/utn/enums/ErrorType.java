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
    checkingReceived(-16,"Exception occurred when checking received messages: "),addErrors(-17,"Exception occurred while adding errors: "),
    createNinja(-18,"Exception creating Ninja: "),createPosition(-19,"Exception occurred creating a ninja position: "),
    moveNinja(-20,"Exception occurred when trying to move the ninja: "),attackReceived(-21,"Exception occurred while processing the attack received: "),
    validatingAttack(-22,"Exception happened when validating attack received: "),canMoveClient(-23,"Exception ocurred while cheking if the client can move: "),
    canAttackClient(-24,"Exception happened when checking if the client can attack: "),validDirectionClient(-25,"Exception occurred when validating the client ninja direction: "),
    moveNinjaClient(-26,"Exception happened trying to move the client ninja: "),deadCommander(-27,"Exception when validating commander's dead: ");

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
