package edu.utn.entity.ninja;


import edu.utn.entity.Board;


public abstract class Ninja implements Movement {

    private String name;
    private int lifePoints;
    private NinjaPosition ninjaPosition;
    private Attack attack;
    private Direction direction;

    public Ninja(String name, int lifePoints, NinjaPosition ninjaPosition, Attack attack) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.ninjaPosition = ninjaPosition;
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
        NinjaPosition current = getNinjaPosition();
        NinjaPosition next = getNinjaPosition().next(getDirection());
        if (!isDestroyed(next) && !isOccupied(next)){
            this.ninjaPosition = next;
            Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(false);
            Board.getInstance().getSquares()[next.getI()][next.getJ()].setHasNinja(true);
        }
        else{
            Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(true);
            this.ninjaPosition =current;
        }
    }

    private boolean isDestroyed(NinjaPosition position){
        int i= position.getI();
        int j= position.getJ();
        if (Board.getInstance().getSquares()[i][j].name().equals("Destroyed")){
            System.out.println("Ese casillero fue destruido, no puedes posicionarte ahi");
            return true;
        }
        else return false;
    }

    private boolean isOccupied(NinjaPosition position){
        int i= position.getI();
        int j= position.getJ();
        if(Board.getInstance().getSquares()[i][j].hasNinja()){
            System.out.println("Ese casillero esta ocupado por un ninja aliado, no puedes posicionarte ahi");
            return true;
        }
        Board.getInstance().getSquares()[i][j].setHasNinja(true);
        return false;
    }
}
