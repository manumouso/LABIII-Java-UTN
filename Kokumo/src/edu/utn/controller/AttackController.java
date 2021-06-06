package edu.utn.controller;

import edu.utn.message.Message;
import edu.utn.model.Board;
import edu.utn.model.Player;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;
import edu.utn.model.square.Destroyed;
import edu.utn.validator.AttackValidator;

public class AttackController {

    private NinjaController ninjaController;
    public NinjaController getNinjaController() {
        if(ninjaController==null){
            ninjaController=new NinjaController();
        }
        return ninjaController;
    }

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
                getNinjaController().checkLifePoints(ninja);
                if(ninja.isDead()){
                    if(previousName.equals("NC")){
                        //message.getMessageMap().put(i,"You WIN, killed: "+player.getName()+"'s ninja commander");
                    }else{
                        //message.getMessageMap().put(i,"You killed one: "+player.getName()+"'s ninja warrior");
                    }
                }else{
                    //message.getMessageMap().put(i,"You hurt a ninja");
                }
            }else{
                //message.getMessageMap().put(i,"You destroyed a square");
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
