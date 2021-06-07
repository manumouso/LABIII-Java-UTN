package edu.utn.view;

import edu.utn.manager.GameManager;

import java.util.Scanner;

public class ServerRoom extends Stage{

    private boolean serverWasCreated;

    public boolean serverWasCreated() {
        return serverWasCreated;
    }

    public void setServerWasCreated(boolean serverWasCreated) {
        this.serverWasCreated = serverWasCreated;
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
                System.out.println("\t\t\t[4].START SERVER");
                System.out.println("\t\t\t[0].GO BACK");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        if(serverWasCreated()){
                            //manager.stopService... LLAMO A LA FUNCION QUE HARA EL STOP SERVER
                        }
                        System.out.println("\n");
                        System.out.println("\t\t\tBye Ninja. ( ^_^)/\n");
                        super.footer();
                    }
                    case 1 -> {
                        if(!serverWasCreated()){
                            Scanner scanner2 =new Scanner(System.in);
                            System.out.print("\t\t\tEnter IP-> ");
                            String IP=scanner2.next();
                            System.out.print("\t\t\tEnter Port-> ");
                            String PORT=scanner2.next();
                            //MODULARIZAR en unan funcion EN MANAGER: TO INT, porque ya lo use en PlayerRoom Tmb
                            try {
                                int port= Integer.parseInt(PORT);
                                manager.setServer(manager.createServer(IP,port));
                                if(manager.getServer()!=null){
                                    setServerWasCreated(true);
                                }
                                manager.printMessages();
                            }catch (NumberFormatException e){
                                System.out.println("Port must be a number");
                            }
                        }else{
                            System.out.println("you have already created the server");
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        //PEDIR POR PANTALLA IP, PUERTO: CREAR OBJ SERVER DEL CLIENTE/otro Jugador
                        //MANDARLE UN REQUEST UNITE A MI, JSON PASARLE MI PUERTO MI IP
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 3 -> {
                        //TENGO QUE RECIBIR UN REQUEST A MI SERVER CON LOS DATOS
                        //IP Y PUERTO DEL SERVER CLIENT
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    case 4 -> {
                        if(serverWasCreated()){
                            //manager.startService... LLAMO A UNA FUNCION QUE HARA START SERVER
                            //MANDO AL PLAYER MENU PARA cree jugador y ninjas
                        }
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();
                    }
                    default -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1,2]");
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
}

