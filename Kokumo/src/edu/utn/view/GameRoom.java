package edu.utn.view;


import edu.utn.enums.MessageType;
import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.ninja.Direction;
import edu.utn.model.ninja.Ninja;


import java.awt.*;
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
            if(manager.getPlayer()==null){
                manager.setPlayer(manager.createPlayer());
            }

            do
            {

                super.header();
                System.out.println("\n\t\t\t\tGAME ROOM");
                System.out.println("\n\t\t\t[1].MOVE");
                System.out.println("\t\t\t[2].ATTACK");
                System.out.println("\t\t\t[3].VIEW NINJAS DATA");
                System.out.println("\t\t\t[4].VIEW BOARDS");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        //ESTE GO BACK TIENE QUE guardo el ESTADO de los ninjas en contador en manager con estado de mov/ataques
                        //entonces estas variables deben ser del manager
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
    private void moveNinjas(GameManager manager){
        if(manager.commanderAlive()) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\t\t\tNINJA DIRECTIONS:");
            System.out.println("\t\t\tN= NORTH");
            System.out.println("\t\t\tNE= NORTH EAST");
            System.out.println("\t\t\tNW= NORTH WEST");
            System.out.println("\t\t\tS=SOUTH");
            System.out.println("\t\t\tSE= SOUTH EAST");
            System.out.println("\t\t\tSW= SOUTH WEST");
            System.out.println("\t\t\tE= EAST");
            System.out.println("\t\t\tW= WEST");
            Map<String, Direction> directionMap = manager.getDirectionsMap();
            int i = 0;
            for (Ninja ninja : manager.getPlayer().getNinjas()) {

                System.out.print("\t\t\tDo you want to move your ninja: " + ninja.getName()+ ":[y/n]");
                String answer = scanner.next();
                if (answer.equals("y") || answer.equals("Y")) {
                    manager.printBoard(true);
                    System.out.println("");
                    System.out.print("\t\t\tEnter the direction ->");
                    String direction = scanner.next();
                    if (directionMap.containsKey(direction)) {
                        if (manager.move(ninja, directionMap.get(direction))) {
                            setMovedNinjas(getMovedNinjas() + 1);
                        }
                        manager.printBoard(true);
                        System.out.println(" ");
                        manager.printMessages();
                        manager.clearMessages();
                    } else {
                        System.out.println(" ");
                        System.out.println("\t\t\tWrong Direction try again!!!");
                    }
                }
                i++;
            }
        }else{
            manager.printMessages();
            manager.clearMessages();
        }

    }
}
