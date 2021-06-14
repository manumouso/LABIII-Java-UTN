package edu.utn.view;




import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;
import edu.utn.model.ninja.NinjaPosition;


import java.util.Map;
import java.util.Scanner;

public class GameRoom extends Stage{

    private boolean wrongPosition;

    public boolean isWrongPosition() {
        return wrongPosition;
    }

    public void setWrongPosition(boolean wrongPosition) {
        this.wrongPosition = wrongPosition;
    }

    @Override
    public void menu(GameManager manager) {
        try {

            int option;

            do
            {

                super.header();
                System.out.println("\n\t\t\t\tGAME ROOM");
                System.out.println("\n\t\t\t[1].MOVE");
                System.out.println("\t\t\t[2].ATTACK");
                System.out.println("\t\t\t[3].VIEW NINJAS DATA");
                System.out.println("\t\t\t[4].VIEW BOARDS");
                System.out.println("\t\t\t[5].CHECK RECEIVED MESSAGES");
                System.out.println("\t\t\t[6].END YOUR TURN");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0:
                        goBack(manager);
                        System.out.println("\n");
                        System.out.println("\t\t\tTo the Player Menu. ( ^_^)/\n");
                        break;

                    case 1:
                        if(manager.getPlayerManager().isMyTurn()){
                            if(manager.getServiceManager().getCorrectMovement()< GameConstants.MAX_NINJAS){
                                moveNinjas(manager);

                            }
                        }else{
                            System.out.println("It's not your turn, wait");
                        }

                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 2:
                        if(manager.getPlayerManager().isMyTurn()){
                            attackNinjas(manager);
                        }else{
                            System.out.println("It's not your turn, wait");
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 3:
                        getInfo(manager);
                        System.out.println();
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;

                    case 4:
                        manager.printBoard(true);
                        manager.printBoard(false);
                        System.out.println(" ");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 5:
                        if(!manager.getPlayerManager().isMyTurn()){
                            manager.checkReceivedMessages();
                        }else{
                            System.out.println("\t\t\tYou can't check your inbox if it is your turn");
                        }
                        System.out.println(" ");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 6:
                        endTurn(manager);
                        System.out.println(" ");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    default:
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2,3,4,5,6]");
                        System.out.println("\t\t\t[0]->Quit Game");
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                }

            } while (option != 0);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void goBack(GameManager manager){
        if(manager.getPlayerManager().getPlayer().getNinjas().size()>0){
            manager.getPlayerManager().clearNinjas();
        }
        manager.clearBoards(true);
        manager.clearBoards(false);
    }

    private void getInfo(GameManager manager){
        System.out.println("\t\t\t NINJAS DATA-----");
        for(Ninja ninja: manager.getPlayerManager().getPlayer().getNinjas()){
            System.out.println("\t\t\tNinja: "+ninja.getName());
            System.out.println("\t\t\tLife Points: "+ninja.getLifePoints());
            System.out.println("\t\t\tNinja position: i: "+ninja.getNinjaPosition().getI()+" j: "+ninja.getNinjaPosition().getJ());
            System.out.println("\t\t\tMovement Allowed: "+(canMove(ninja)?"yes":"no"));
            System.out.println("\t\t\t State: "+(ninja.isDead()?"DEAD":"ALIVE"));
            System.out.println(" ");
        }
    }
    private boolean canMove(Ninja ninja){
        return ninja.getMovementCounter() == 0;
    }
    private void moveNinjas(GameManager manager){
        if(manager.getRuleManager().commanderAlive(manager.getPlayerManager().getPlayer())) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\tNINJA DIRECTIONS (case insensitive):");
            System.out.println("\t\t\t[N]=NORTH");
            System.out.println("\t\t\t[NE]=NORTH EAST");
            System.out.println("\t\t\t[NW]=NORTH WEST");
            System.out.println("\t\t\t[S]=SOUTH");
            System.out.println("\t\t\t[SE]=SOUTH EAST");
            System.out.println("\t\t\t[SW]=SOUTH WEST");
            System.out.println("\t\t\t[E]=EAST");
            System.out.println("\t\t\t[W]=WEST");
            System.out.println(" ");
            Map<String, Direction> directionMap = manager.getRuleManager().getDirectionsMap();

            for (Ninja ninja : manager.getPlayerManager().getPlayer().getNinjas()) {
                if(manager.getRuleManager().movementAllowed(ninja) && manager.getRuleManager().isAlive(ninja)){
                    System.out.print("\t\t\tDo you want to move your ninja-> " + ninja.getName()+ "  [y/n]: ");
                    String answer = scanner.next();
                    System.out.println(" ");
                    if (answer.equals("y") || answer.equals("Y")) {
                        manager.printBoard(true);
                        System.out.println(" ");
                        System.out.print("\t\t\tEnter the direction ->");
                        String direction=scanner.next();
                        while(!directionMap.containsKey(direction)){
                            System.out.println(" ");
                            System.out.println("\t\t\tWrong Direction try again!!!");
                            System.out.print("\t\t\tEnter the direction ->");
                            direction = scanner.next();
                        }
                        if (manager.getRuleManager().move(ninja, directionMap.get(direction))) {
                            manager.getServiceManager().setCorrectMovement(manager.getServiceManager().getCorrectMovement()+1);

                        }
                        manager.printBoard(true);
                        System.out.println(" ");
                        print(manager);
                    }
                }else{
                    print(manager);
                }
            }
        }else{
            print(manager);
        }
    }


    private void attackNinjas(GameManager manager){

            Scanner scanner = new Scanner(System.in);
            NinjaPosition ninjaPosition=null;
            for (Ninja ninja : manager.getPlayerManager().getPlayer().getNinjas()) {
                if(ninja.getAttackCounter()==0 && !ninja.isDead()){
                    System.out.print("\t\t\tDo you want to attack with-> " + ninja.getName()+ "  [y/n]: ");
                    String answer = scanner.next();
                    System.out.println(" ");
                    if (answer.equals("y") || answer.equals("Y")) {
                        manager.printBoard(false);
                        System.out.println(" ");
                        System.out.println("\t\t\tEnter the attack position:");
                        ninjaPosition=inputPosition(manager);

                        manager.sendAttack(ninjaPosition, ninja.getAttackPoints());
                        ninja.setAttackCounter(ninja.getAttackCounter()+1);
                        ninja.setMovementCounter(0);
                        manager.printBoard(false);
                        System.out.println(" ");

                    }
                }else{
                    System.out.println("\t\t\tThis ninja can't attack. Ninja: "+ninja.getName());
                }
            }

    }
    private NinjaPosition inputPosition(GameManager manager){
        setWrongPosition(true);
        Scanner scanner =new Scanner(System.in);
        NinjaPosition attackPosition = null;
        while(isWrongPosition()){
          System.out.print("\t\t\tEnter the attack position ROW: position I: [expected number 0 to 4 included] -> ");
          String posI = scanner.next();
          System.out.print("\t\t\tEnter the attack position COLUMN: position J: [expected number 0 to 4 included] -> ");
          String posJ= scanner.next();
          attackPosition=toInt(posI,posJ,manager);

        }
        return attackPosition;
    }

    private NinjaPosition toInt(String posI,String posJ,GameManager manager){
        NinjaPosition attackPosition=null;
        try{
            int positionI= Integer.parseInt(posI);
            int positionJ= Integer.parseInt(posJ);

            attackPosition=manager.getRuleManager().createPosition(positionI,positionJ);
            if(attackPosition!=null){
                setWrongPosition(false);
            }
            System.out.println(" ");

        }catch (NumberFormatException e){
            System.out.println("\t\t\tRemember only numbers allowed.");
            System.out.println("\t\t\tTry again!!!");
            setWrongPosition(true);
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
            setWrongPosition(true);
        }
        return attackPosition;
    }


    private void endTurn(GameManager manager){
        if(manager.getPlayerManager().isMyTurn()){
            if(manager.getServiceManager().getCorrectMovement()==GameConstants.MAX_NINJAS){
                manager.getServiceManager().setCorrectMovement(0);
                manager.getPlayerManager().setMyTurn(false);
                manager.sendEndTurn();
            }else{
                System.out.println("\t\t\tYou have movements(move or attack) left: "+ (GameConstants.MAX_NINJAS-manager.getServiceManager().getCorrectMovement()));
            }
        }else{
            System.out.println("\t\t\tIt's not your turn, wait");
        }
    }

}
