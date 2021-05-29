package edu.utn.entity.ninja;


import edu.utn.entity.Board;
import edu.utn.validator.PositionValidator;


public abstract class Ninja implements Movement {

    private String name;
    private int lifePoints;
    private int attackPoints;
    private NinjaPosition ninjaPosition;
    private Attack attack;
    private Direction direction;

    public Ninja(String name, int lifePoints, int attackPoints, NinjaPosition ninjaPosition, Attack attack) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints=attackPoints;
        this.ninjaPosition = ninjaPosition;
        Board.getInstance().getSquares()[ninjaPosition.getI()][ninjaPosition.getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninjaPosition.getI()][ninjaPosition.getJ()].ninjaStandsOn(this);
        this.checkLifePoints();
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public NinjaPosition getNinjaPosition() {
        return ninjaPosition;
    }

    public void setNinjaPosition(NinjaPosition ninjaPosition) {
        this.ninjaPosition = ninjaPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Attack getAttack() {
        return attack;
    }

    @Override
    public void move() {
        if(!ninjaDead()){
            NinjaPosition current = getNinjaPosition();
            NinjaPosition next = getNinjaPosition().next(getDirection());
            if(PositionValidator.validPosition(next)){
                if (!PositionValidator.isDestroyed(next) && !PositionValidator.isOccupied(next)){
                    this.ninjaPosition = next;
                    Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(false);
                    Board.getInstance().getSquares()[next.getI()][next.getJ()].setHasNinja(true);
                    Board.getInstance().getSquares()[next.getI()][next.getJ()].ninjaStandsOn(this);
                    this.checkLifePoints();
                }
            }else{
                Board.getInstance().getMessages().getErrorMap().put(-3,"Invalid move, trying to leave the board");
            }
        }else{
            Board.getInstance().getMessages().getErrorMap().put(-4,"Dead ninjas can't move");
        }
    }

    private boolean ninjaDead(){
        return getName().equals("dead");
    }
    private void checkLifePoints(){
        if(getLifePoints()<=0){
            setName("dead");
        }
    }
}
