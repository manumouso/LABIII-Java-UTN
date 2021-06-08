package edu.utn.view;

import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.Board;
import edu.utn.model.ninja.Ninja;


import java.util.Scanner;

public class PlayerRoom extends Stage{
    private int ninjaCounter;

    public int getNinjaCounter() {
        return ninjaCounter;
    }
    public void ninjaCreated(){
        ninjaCounter++;
    }
    public void setNinjaCounter(int ninjaCounter) {
        this.ninjaCounter = ninjaCounter;
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
                System.out.println("\n\t\t\t\tPLAYER MENU");
                System.out.println("\n\t\t\t[1].ENTER NAME");
                System.out.println("\t\t\t[2].PLACE YOUR NINJAS");
                System.out.println("\t\t\t[3].VIEW NINJAS BOARD");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tTo the Main Menu. ( ^_^)/\n");

                    }
                    case 1 -> {
                        System.out.print("\t\t\tEnter your name-> ");
                        String name= scanner.next();
                        manager.setPlayerName(name);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        if(getNinjaCounter()<GameConstants.MAX_NINJAS){
                            Ninja ninja=inputPosition("commander",true,manager);
                            manager.addNinjaToPlayer(ninja);
                            ninjaCreated();
                            ninja=inputPosition("warrior 1",false,manager);
                            manager.addNinjaToPlayer(ninja);
                            ninjaCreated();
                            ninja=inputPosition("warrior 2",false,manager);
                            manager.addNinjaToPlayer(ninja);
                            ninjaCreated();
                            System.out.println("\t\t\tNinjas placed correctly");
                        }else{
                            System.out.println("\t\t\tYou already placed the ninjas correctly");
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 3 -> {
                        manager.printBoard(true);
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

    private Ninja inputPosition(String name, boolean commander, GameManager manager){
        boolean wrongPosition=true;
        Scanner scanner =new Scanner(System.in);
        Ninja ninja = null;
        while(wrongPosition){
            System.out.print("\t\t\tEnter "+ name+" position I: [expected number 0 to 4 included] -> ");
            String posI = scanner.next();
            System.out.print("\t\t\tEnter "+ name+" position J: [expected number 0 to 4 included] -> ");
            String posJ= scanner.next();
            try{
                int positionI= Integer.parseInt(posI);
                int positionJ= Integer.parseInt(posJ);

                ninja = manager.createNinja(positionI,positionJ,commander);
                if(ninja!=null){
                    wrongPosition=false;
                }
                manager.printMessages();
                manager.clearMessages();
            }catch (NumberFormatException e){
                System.out.println("\t\t\tRemember only numbers allowed.");
                System.out.println("\t\t\tTry again!!!");
                wrongPosition=true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ninja;
    }
}
