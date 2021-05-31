package edu.utn.model.ninja;


import edu.utn.model.Board;
import edu.utn.model.Player;
import edu.utn.model.square.Destroyed;
import edu.utn.message.Message;
import edu.utn.validator.AttackValidator;
import edu.utn.validator.MovementValidator;

import javax.json.Json;
import javax.json.JsonObject;


public abstract class Ninja implements Movement,Attack {

    private String name;
    private int lifePoints;
    private int attackPoints;
    private NinjaPosition ninjaPosition;
    private Direction direction;
    private int movementCounter;

    public Ninja(String name, int lifePoints, int attackPoints, NinjaPosition ninjaPosition) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints=attackPoints;
        this.ninjaPosition = ninjaPosition;
        Board.getInstance().getSquares()[ninjaPosition.getI()][ninjaPosition.getJ()].setHasNinja(true);
        Board.getInstance().getSquares()[ninjaPosition.getI()][ninjaPosition.getJ()].ninjaStandsOn(this);
        this.checkLifePoints();
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

    public int getMovementCounter() {
        return movementCounter;
    }

    public void setMovementCounter(int movementCounter) {
        this.movementCounter = movementCounter;
    }

    private boolean ninjaDead(){
        return getName().equals("dead");
    }
    public void checkLifePoints(){
        if(getLifePoints()<=0){
            setName("dead");
        }
    }

    @Override
    public void move() {
        if (!MovementValidator.ninjaDead(getName()) && !MovementValidator.tryingConsecutiveMove(getMovementCounter(),getName())) {
            NinjaPosition current = getNinjaPosition();
            NinjaPosition next = getNinjaPosition().next(getDirection());
            if (MovementValidator.validPosition(next)) {
                if (!MovementValidator.isDestroyed(next) && !MovementValidator.isOccupied(next)) {
                    this.ninjaPosition = next;
                    Board.getInstance().getSquares()[current.getI()][current.getJ()].setHasNinja(false);
                    Board.getInstance().getSquares()[next.getI()][next.getJ()].setHasNinja(true);
                    Board.getInstance().getSquares()[next.getI()][next.getJ()].ninjaStandsOn(this);
                    this.checkLifePoints();
                    //this.movementCounter++; por ahora COMENTADO, PORQUE NO DECIDI EL PUNTO DEL PROGRAMA
                    //DONDE ESTE CONTADOR VUELVE A CERO, Y EVITA QUE SE HAGAN MOV CONSECUTIVOS
                    //seguramente despues de atacar
                }
            }
        }
    }
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("ninjaName", this.name)
                .add("lifePoints", this.lifePoints)
                .build();
    }

   //EN CONSTRUCCION :
    //MODULARIZAR LAS VALIDACIONES A ATTACK VALIDATOR
   //esto puede ir a un controller, y que el ataque del ninja sea llamar
   //a la construccion del objeto Json, empaquetar y mandarlo, y esto es la logica de negocio
   //que tengo que meter solo en server
   @Override
   public void ninjaAttack(Player player){
       //probablemente estos los reciba por parametro
       // arrays y list representativos del body: que saco y meto en json(aca saco)
        NinjaPosition[] attackPosition=new NinjaPosition[3];//sino ataca lo que llega pueden ser (-1;-1)
        Integer[] attackPoints = new Integer[3];
        Message message=new Message();
        int i=0;
        for(Ninja ninja: player.getNinjas()){
            if(ninja.getNinjaPosition().getI()==attackPosition[i].getI() && ninja.getNinjaPosition().getJ()==attackPosition[i].getJ()){
                ninja.setLifePoints(ninja.getLifePoints()-attackPoints[i]);
                String previousName= ninja.getName();
                ninja.checkLifePoints();
                if(ninjaDead()){
                    if(previousName.equals("NC")){
                        message.getMessageList().add(i,"You WIN, killed: "+player.getName()+"'s ninja commander");
                    }else{
                        message.getMessageList().add(i,"You killed one: "+player.getName()+"'s ninja warrior");
                    }
                }else{
                    message.getMessageList().add(i,"You hurt a ninja");
                }
            }else{
                message.getMessageList().add(i,"You destroyed a square");
                Board.getInstance().getSquares()[attackPosition[i].getI()][attackPosition[i].getJ()]=new Destroyed();
            }
            i++;
        }
        //probablemente aca tenga que crear el obj json con message list que ira en el response body
        //y segun la rta marco o no al casillero como destruido en mi tablero de ataques,
        //entonces seguro necesito crear otra clase que sea direccion elegida para el ataque
        //un punto intermedio hasta que vuelva rta, si la rta nunca vuelve, entonces que repita el turno
        //si es que la conexion sigue activa
   }
   public void cleanNinjaAttack(Player player) {
        //probablemente estos los reciba por parametro
        // arrays y list representativos del body: que saco y meto en json(aca saco)
        NinjaPosition[] attackPosition = new NinjaPosition[3];//sino ataca lo que llega pueden ser (-1;-1)
        Integer[] attackPoints = new Integer[3];
        Message message = new Message();
        int i = 0;
        for (Ninja ninja : player.getNinjas()) {
            AttackValidator.hitNinja(ninja/*,aca necesito pasarle lo json*/);
            i++;
        }
   }

}
