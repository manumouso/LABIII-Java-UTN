package edu.utn.view;

import edu.utn.manager.GameManager;

import java.io.IOException;
import java.util.Scanner;

public class ServerRoom extends Stage{


    @Override
    public void menu(GameManager manager) {
        try {

            int option;
            do
            {
                super.header();
                System.out.println("\n\t\t\t\tSERVER MENU");
                System.out.println("\n\t\t\t[1].CREATE SERVER");
                System.out.println("\t\t\t[2].JOIN GAME");
                System.out.println("\t\t\t[3].HOST GAME");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0:
                        goBack(manager);
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 1:
                        start(manager);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 2:
                        sendJoin(manager);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    case 3:
                        if(waitToProceed(manager)){
                            manager.getPlayerManager().setMyTurn(true);
                            manager.toPlayerRoom();
                        }
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                        break;
                    default:
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2,3]");
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

    private void goBack(GameManager manager) throws IOException {
        if(manager.serverWasCreated()){
            if(manager.isRunning()){
                manager.closeConnection();
                manager.setRunning(false);
            }
            if(manager.connectedClient()){
                manager.setExternalMessage(false);
                manager.setConnectedClient(false);
            }
            manager.setServerWasCreated(false);

        }
    }

    private void start(GameManager manager) throws IOException {
        if(!manager.serverWasCreated()){
            Scanner scanner2 =new Scanner(System.in);
            System.out.print("\t\t\tEnter IP-> ");
            String IP=scanner2.next();
            System.out.print("\t\t\tEnter Port-> ");
            String PORT=scanner2.next();

            try {
                int port= Integer.parseInt(PORT);
                manager.setServer(IP,port);
                if(manager.getServer()!=null){
                    manager.setServerState();
                    manager.setServerWasCreated(true);
                    manager.setClient();
                }
                print(manager);
            }catch (NumberFormatException e){
                System.out.println("\t\t\tPort must be a number");
            }catch (Exception e){
                System.out.println("Exception: "+ e.getMessage());
            }
        }else{
            System.out.println("\t\t\tYou have already created the server");

        }
        if(manager.serverWasCreated() && !manager.isRunning()){
            manager.startConnection();
            manager.setRunning(true);
            System.out.println("\t\t\tServer Running at IP: "+manager.getServiceManager().getServer().getIP()+" and port: "+manager.getServiceManager().getServer().getPort());
        }
    }

    private boolean waitToProceed(GameManager manager){
        if(!manager.connectedClient()){
            if(manager.serverWasCreated() && manager.isRunning()){
                while(!manager.externalMessageReceived()){}
                manager.setConnectedClient(true);
                System.out.println("\t\t\tConnected to the client");
            }else{
                System.out.println("\t\t\tFirst create the server");
            }
        }
        return manager.connectedClient();
    }

    private void sendJoin(GameManager manager){

        if(manager.serverWasCreated() && manager.isRunning()){
            Scanner scanner2 =new Scanner(System.in);
            System.out.print("\t\t\tEnter IP-> ");
            String IP=scanner2.next();
            System.out.print("\t\t\tEnter Port-> ");
            String PORT=scanner2.next();
            try {
                int port= Integer.parseInt(PORT);
                manager.sendJoin(IP,port);
                manager.setConnectedClient(true);

            }catch (NumberFormatException e){
                System.out.println("\t\t\tPort must be a number");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(manager.connectedClient()){
                while(!manager.getServiceManager().isRequestSuccessful()){}
                manager.getPlayerManager().setMyTurn(false);
                System.out.println("\t\t\tConnected to the server");
                manager.toPlayerRoom();
            }
        }else{
            System.out.println("\t\t\tFirst create the server");
        }

    }
}

