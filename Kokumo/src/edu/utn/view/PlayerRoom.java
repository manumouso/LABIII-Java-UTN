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
            if(manager.getPlayerManager().getPlayer()==null){
                manager.getPlayerManager().setPlayer(manager.getPlayerManager().createPlayer());
            }

            do
            {
                super.cleanConsole();
                super.header();
                super.printError(manager);
                System.out.println("\n\t\t\t\tPLAYER MENU");
                System.out.println("\n\t\t\t[1].ENTER NAME");
                System.out.println("\t\t\t[2].PLACE YOUR NINJAS TO ADVANCE");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0:
                        goBack(manager);
                        System.out.println("\n");
                        System.out.println("\t\t\tTo the Server Menu. ( ^_^)/\n");
                        break;
                    case 1:
                        System.out.print("\t\t\tEnter your name-> ");
                        String name= scanner.next();
                        manager.getPlayerManager().setPlayerName(name);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 2:
                        if(manager.getRuleManager().lessThanRequiredNinjasQuantity(manager.getPlayerManager().getPlayer())){
                            placeNinjas(manager);
                            System.out.println("\t\t\tNinjas placed correctly");
                        }
                        if(manager.getRuleManager().requiredNinjasQuantity(manager.getPlayerManager().getPlayer())){
                            manager.toGameRoom();

                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    default:
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2]");
                        System.out.println("\t\t\t[0]->GO BACK");
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                }

            } while (option != 0);

        }catch (Exception e){
            System.out.println("\t\t\tException: "+e.getMessage());

        }

    }
    private void goBack(GameManager manager){
        if(manager.connectedClient()){
            manager.setExternalMessage(false);
            manager.setConnectedClient(false);
        }
        if(manager.getPlayerManager().getPlayer().getNinjas().size()>0){
            manager.getPlayerManager().clearNinjas();
        }
        manager.clearBoards(true);
    }

    private void addNinja(Ninja ninja,GameManager manager){
        manager.getPlayerManager().addNinjaToPlayer(ninja);

    }
    private void placeNinjas(GameManager manager){
        System.out.println(" ");
        System.out.println("\t\t\tYou will be placing your NINJA COMMANDER FIRST");
        System.out.println("\t\t\tThen you will place the NINJA WARRIORS");
        Ninja ninja;
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
            manager.printBoard(true);
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

            ninja = manager.getRuleManager().createNinja(positionI,positionJ,commander,i);
            if(ninja!=null){
                setWrongPosition(false);
            }
            System.out.println(" ");
            printMessages(manager);
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
