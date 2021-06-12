package edu.utn.view;



import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;



import java.util.Map;
import java.util.Scanner;

public class GameRoom extends Stage{
    private int movedNinjas;
    private int attackedNinjas;

    public int getMovedNinjas() {
        return movedNinjas;
    }

    public void setMovedNinjas(int movedNinjas) {
        this.movedNinjas = movedNinjas;
    }

    public int getAttackedNinjas() {
        return attackedNinjas;
    }

    public void setAttackedNinjas(int attackedNinjas) {
        this.attackedNinjas = attackedNinjas;
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
                System.out.println("\t\t\t[5].WAIT ATTACK AND PRINT");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        goBack(manager);
                        System.out.println("\n");
                        System.out.println("\t\t\tTo the Player Menu. ( ^_^)/\n");

                    }
                    case 1 -> {
                        if(getMovedNinjas()< GameConstants.MAX_NINJAS){
                            moveNinjas(manager);
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {

                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 3 -> {
                        System.out.println();
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 4 -> {
                        manager.printBoard(true);
                        manager.printBoard(false);
                        System.out.println("");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 5 -> {
                        manager.waitAndPrint();
                        System.out.println("");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    default -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2,3]");
                        System.out.println("\t\t\t[0]->Quit Game");
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
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
        //manager.clearBoards(false);
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
                            setMovedNinjas(getMovedNinjas() + 1);
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

}
