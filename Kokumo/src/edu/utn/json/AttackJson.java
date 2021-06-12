package edu.utn.json;


import edu.utn.model.ninja.NinjaPosition;


import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class AttackJson {

    private List<NinjaPosition> attackPositions;
    private List<Integer> attackPoints;
    private List<String> message;

    //input
    public AttackJson(JsonObject object) {

        JsonArray positions = object.getJsonArray("positions");
        JsonArray points = object.getJsonArray("points");
        JsonArray messages = object.getJsonArray("messages");

        this.attackPositions = attackPositionsJsonToObj(positions);
        this.attackPoints= attackPointsJsonToObj(points);
        this.message = msgJsonToObj(messages);
    }
    //output
    public AttackJson(List<NinjaPosition> attackPositions, List<Integer> attackPoints, List<String> message) {
        this.attackPositions = attackPositions;
        this.attackPoints = attackPoints;
        this.message = message;
    }

    public List<NinjaPosition> getAttackPositions() {
        return attackPositions;
    }

    public List<Integer> getAttackPoints() {
        return attackPoints;
    }

    public List<String> getMessage() {
        return message;
    }

    //no voy a hacer estas funciones con for por ahora
    private List<NinjaPosition> attackPositionsJsonToObj(JsonArray positions){

        JsonArray ninjaPos1 = positions.getJsonArray(0);
        JsonArray ninjaPos2 = positions.getJsonArray(1);
        JsonArray ninjaPos3 = positions.getJsonArray(2);
        //aca podria validar el input, del json aunque en el client tmb lo haga
        //si el input es invalido puedo usarlo para asumir que ese ninja no ataco y se movio
        //como para no mandar nulos que nose que pasa(todavia)
        NinjaPosition pos1 = new NinjaPosition(ninjaPos1.getInt(0),ninjaPos1.getInt(1));
        NinjaPosition pos2 = new NinjaPosition(ninjaPos2.getInt(0),ninjaPos2.getInt(1));
        NinjaPosition pos3 = new NinjaPosition(ninjaPos3.getInt(0),ninjaPos3.getInt(1));

        List<NinjaPosition> attackPos =new ArrayList<>();
        attackPos.add(pos1);
        attackPos.add(pos2);
        attackPos.add(pos3);

        return attackPos;
    }

    private List<Integer> attackPointsJsonToObj(JsonArray points){

        int ninjaAtta1= points.getInt(0);
        int ninjaAtta2= points.getInt(1);
        int ninjaAtta3= points.getInt(2);

        List<Integer> attackPoints = new ArrayList<>();
        attackPoints.add(ninjaAtta1);
        attackPoints.add(ninjaAtta2);
        attackPoints.add(ninjaAtta3);

        return attackPoints;
    }

    private List<String> msgJsonToObj(JsonArray messages){
        String msg1 = messages.getString(0);
        String msg2 = messages.getString(1);
        String msg3 = messages.getString(2);

        List<String> message =new ArrayList<>();
        message.add(msg1);
        message.add(msg2);
        message.add(msg3);

        return message;
    }


}
