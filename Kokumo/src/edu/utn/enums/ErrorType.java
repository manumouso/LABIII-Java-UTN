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
    moveNinjaClient(-26,"Exception happened trying to move the client ninja: "),deadCommander(-27,"Exception when validating commander's dead: "),withinLimits(-28,"Exception when validating position within the limits of board"),
    freeSquare(-29,"Exception occurred when validating free square: "),isAlive(-30,"Exception occurred when validating ninja is alive: "),
    movementAllowed(-31,"Exception occurred when validating if movement allowed: "),squarePassable(-32,"Exception occurred when validating if square was passable: "),
    requiredQuantity(-33,"Exception happened when checking required ninjas quantity: "),lessQuantity(-34,"Exception occurred when checking less than required ninjas quantity: "),
    moveThisTurn(-35,"Exception happened when checking if the ninja can move this turn: "),diedTrap(-36,"Exception occurred when adding message of enemy ninja dying on a trap: "),invitationReceived(-37,"Exception occurred when processing the invitation: "),
    win(-38,"Exception occurred when processing the winner: "),lose(-39,"Exception occurred when processing the loser: "),validServerAttack(-40,"Exception occurred when validating the servers attack: ");

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
