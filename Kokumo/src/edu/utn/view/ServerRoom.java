package edu.utn.view;

import edu.utn.manager.GameManager;

import java.io.IOException;
import java.util.Scanner;

public class ServerRoom extends Stage{

    private boolean serverWasCreated;

    public boolean serverWasCreated() {
        return serverWasCreated;
    }

    public void setServerWasCreated(boolean serverWasCreated) {
        this.serverWasCreated = serverWasCreated;
    }

    private boolean running;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean requestSent;

    public boolean requestSent() {
        return requestSent;
    }

    public void setRequestSent(boolean requestSent) {
        this.requestSent = requestSent;
    }

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
                        if(serverWasCreated()){
                           manager.closeConnection();
                           setServerWasCreated(false);
                           setRunning(false);
                        }
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
                        //sendRequest(manager); construccion
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 3 -> {
                        if(serverWasCreated() && isRunning()){
                            while(!manager.isResponse()){}
                            System.out.println("\t\t\tConnected to the client");
                            manager.toPlayerRoom();
                        }else{
                            System.out.println("\t\t\tFirst create the server");
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

    private void start(GameManager manager) throws IOException {
        if(!serverWasCreated()){
            Scanner scanner2 =new Scanner(System.in);
            System.out.print("\t\t\tEnter IP-> ");
            String IP=scanner2.next();
            System.out.print("\t\t\tEnter Port-> ");
            String PORT=scanner2.next();

            try {
                int port= Integer.parseInt(PORT);
                manager.setServer(manager.createServer(IP,port));
                if(manager.getServer()!=null){
                    setServerWasCreated(true);
                }
                manager.printMessages();
                manager.clearMessages();
            }catch (NumberFormatException e){
                System.out.println("\t\t\tPort must be a number");
            }
        }else{
            System.out.println("\t\t\tyou have already created the server");

        }
        if(!isRunning()){
            manager.startConnection();
            setRunning(true);
            System.out.println("\t\tServer Running at IP: "+manager.getServer().getIP()+" and port: "+manager.getServer().getPort());
        }
    }

    private void sendRequest(GameManager manager){

        Scanner scanner2 =new Scanner(System.in);
        System.out.print("\t\t\tEnter IP-> ");
        String IP=scanner2.next();
        System.out.print("\t\t\tEnter Port-> ");
        String PORT=scanner2.next();
        try {
            int port= Integer.parseInt(PORT);
            //crear un Objeto server del CLient
            //mandar request a esa direccion
        }catch (NumberFormatException e){
            System.out.println("\t\t\tPort must be a number");
        }
        if(serverWasCreated() && isRunning() && requestSent()){

            System.out.println("\t\t\tConnected to the client");
            manager.toPlayerRoom();
        }
    }
}

