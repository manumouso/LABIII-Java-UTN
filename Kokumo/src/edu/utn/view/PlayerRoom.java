package edu.utn.view;

import edu.utn.manager.GameConstants;
import edu.utn.manager.GameManager;
import edu.utn.model.ninja.Ninja;


import java.util.Scanner;

public class PlayerRoom extends Stage{

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
                System.out.println("\t\t\t[4].TO THE GAME ROOM");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        goBack(manager);
                        System.out.println("\n");
                        System.out.println("\t\t\tTo the Server Menu. ( ^_^)/\n");

                    }
                    case 1 -> {
                        System.out.print("\t\t\tEnter your name-> ");
                        String name= scanner.next();
                        manager.setPlayerName(name);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        if(manager.getPlayer().getNinjas().size()<GameConstants.MAX_NINJAS){
                            placeNinjas(manager);
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
                    case 4 -> {
                        if(manager.getPlayer().getNinjas().size()==GameConstants.MAX_NINJAS){
                            manager.toGameRoom();
                        }else{
                            System.out.println("\t\t\tFirst place your ninjas on the board");
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    default -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2,3,4]");
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
        if(manager.connectedClient()){
            manager.setExternalMessage(false);
            manager.setConnectedClient(false);
        }
        if(manager.getPlayer().getNinjas().size()>0){
            manager.clearNinjas();
        }
        manager.clearBoards(true);
    }

    private void addNinja(Ninja ninja,GameManager manager){
        manager.addNinjaToPlayer(ninja);

    }
    private void placeNinjas(GameManager manager){
        System.out.println(" ");
        System.out.println("\t\t\tYou will be placing your NINJA COMMANDER FIRST");
        System.out.println("\t\t\tThen you will place the NINJA WARRIORS");
        Ninja ninja=null;
        for(int i=0;i<GameConstants.MAX_NINJAS;i++){
            if(i==0){
                ninja=inputPosition("commander",true,manager,i);
            }else{
                ninja=inputPosition("warrior",false,manager,i);
            }
            addNinja(ninja,manager);
        }
    }
    private Ninja inputPosition(String name, boolean commander, GameManager manager,int i){
        setWrongPosition(true);
        Scanner scanner =new Scanner(System.in);
        Ninja ninja = null;
        while(isWrongPosition()){
            if(i==0){
                System.out.print("\t\t\tEnter "+ name+" ROW: position I: [expected number 0 to 4 included] -> ");
                String posI = scanner.next();
                System.out.print("\t\t\tEnter "+ name+" COLUMN: position J: [expected number 0 to 4 included] -> ");
                String posJ= scanner.next();
                ninja=toInt(posI,posJ,ninja,manager,commander,i);

            }else{
                System.out.print("\t\t\tEnter "+ name+" "+i+" ROW: position I: [expected number 0 to 4 included] -> ");
                String posI = scanner.next();
                System.out.print("\t\t\tEnter "+ name+" "+i+" COLUMN: position J: [expected number 0 to 4 included] -> ");
                String posJ= scanner.next();
                ninja=toInt(posI,posJ,ninja,manager,commander,i);
            }
        }
        return ninja;
    }

    private Ninja toInt(String posI,String posJ,Ninja ninja, GameManager manager, boolean commander,int i){
        try{
            int positionI= Integer.parseInt(posI);
            int positionJ= Integer.parseInt(posJ);

            ninja = manager.createNinja(positionI,positionJ,commander,i);
            if(ninja!=null){
                setWrongPosition(false);
            }
            System.out.println(" ");
            print(manager);
        }catch (NumberFormatException e){
            System.out.println("\t\t\tRemember only numbers allowed.");
            System.out.println("\t\t\tTry again!!!");
            setWrongPosition(true);
        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());
            setWrongPosition(true);
        }
        return ninja;
    }

}
