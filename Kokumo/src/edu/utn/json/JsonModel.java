package edu.utn.json;


import edu.utn.model.ninja.NinjaPosition;
import edu.utn.message.Message;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JsonModel {

    private NinjaPosition[] attackPositions;
    private int[] attackPoints;
    private Message message;

    //validation of the json on the client but also add validations here later
    //input
    public JsonModel(JsonObject object) {
        JsonArray positions = object.getJsonArray("positions");
        JsonArray points = object.getJsonArray("points");
        JsonArray messages = object.getJsonArray("messages");

        this.attackPositions = attackPosJsonToObj(positions);
        this.attackPoints= attackPointsJsonToObj(points);
        this.message = msgJsonToObj(messages);
    }
    //output
    public JsonModel(NinjaPosition[] attackPositions, int[] attackPoints, Message message) {
        this.attackPositions = attackPositions;
        this.attackPoints = attackPoints;
        this.message = message;
    }

    public NinjaPosition[] getAttackPositions() {

        return attackPositions;
    }

    public int[] getAttackPoints() {
        return attackPoints;
    }

    public Message getMessage() {
        return message;
    }
    //no voy a hacer estas funciones con for por ahora
    private NinjaPosition[] attackPosJsonToObj(JsonArray positions){

        JsonArray ninjaPos1 = positions.getJsonArray(0);
        JsonArray ninjaPos2 = positions.getJsonArray(1);
        JsonArray ninjaPos3 = positions.getJsonArray(2);
        //aca podria validar el input, del json aunque en el client tmb lo haga
        //si el input es invalido puedo usarlo para asumir que ese ninja no ataco y se movio
        //como para no mandar nulos que nose que pasa(todavia)
        NinjaPosition pos1 = new NinjaPosition(ninjaPos1.getInt(0),ninjaPos1.getInt(1));
        NinjaPosition pos2 = new NinjaPosition(ninjaPos2.getInt(0),ninjaPos2.getInt(1));
        NinjaPosition pos3 = new NinjaPosition(ninjaPos3.getInt(0),ninjaPos3.getInt(1));

        NinjaPosition[] attackPos =new NinjaPosition[3];
        attackPos[0]=pos1;
        attackPos[1]=pos2;
        attackPos[2]=pos3;

        return attackPos;
    }

    private int[] attackPointsJsonToObj(JsonArray points){

        int ninjaAtta1= points.getInt(0);
        int ninjaAtta2= points.getInt(1);
        int ninjaAtta3= points.getInt(2);
        //aca podria validar que los puntos de ataque no sean negativos, porque sino suman vida
        int[] attackPoints = new int[3];
        attackPoints[0]=ninjaAtta1;
        attackPoints[1]=ninjaAtta2;
        attackPoints[2]=ninjaAtta3;

        return attackPoints;
    }

    private Message msgJsonToObj(JsonArray messages){
        String msg1 = messages.getString(0);
        String msg2 = messages.getString(1);
        String msg3 = messages.getString(2);
        //libertad de mensajes
        Message message =new Message();
        message.getMessageMap().put(201,msg1);
        message.getMessageMap().put(202,msg2);
        message.getMessageMap().put(203,msg3);

        return message;
    }
}
