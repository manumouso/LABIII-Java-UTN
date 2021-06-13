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
                System.out.println("\t\t\t[2].INVITE PLAYER");
                System.out.println("\t\t\t[3].AWAIT REQUEST");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        goBack(manager);
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 1 -> {
                        start(manager);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        //PEDIR POR PANTALLA IP, PUERTO: CREAR OBJ SERVER DEL CLIENTE/otro Jugador
                        //MANDARLE UN REQUEST UNITE A MI, JSON PASARLE MI PUERTO MI IP
                        sendRequest(manager);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 3 -> {
                        //aca falta guardarme el objeto server del client/crearlo con la IP Y PUERTO
                        //cuando cree el requestHandler await en mi Server
                        if(waitToProceed(manager)){
                            manager.toPlayerRoom();
                        }
                        System.out.println("\n");
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

    private void sendRequest(GameManager manager){

        if(manager.serverWasCreated() && manager.isRunning()){
            Scanner scanner2 =new Scanner(System.in);
            System.out.print("\t\t\tEnter IP-> ");
            String IP=scanner2.next();
            System.out.print("\t\t\tEnter Port-> ");
            String PORT=scanner2.next();
            try {
                int port= Integer.parseInt(PORT);
                manager.sendInvitation(IP,port);
                manager.setConnectedClient(true);

            }catch (NumberFormatException e){
                System.out.println("\t\t\tPort must be a number");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(manager.connectedClient()){
                while(!manager.getServiceManager().isRequestSuccessful()){}
                System.out.println("\t\t\tConnected to the client");
                manager.toPlayerRoom();
            }
        }else{
            System.out.println("First create the server");
        }

    }
}

