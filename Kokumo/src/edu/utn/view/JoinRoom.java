package edu.utn.view;

import java.util.Scanner;

public class JoinRoom extends Stage{
    @Override
    public void menu() {
        try {

            int option;
            do
            {

                super.header();
                System.out.println("\n\t\t\t\tUNIRSE PARTIDA\n");
                System.out.println("\n\t\t\t[1].INGRESAR IP\n");
                System.out.println("\t\t\t[0].SALIR\n");
                System.out.print("\n\t\t\tSeleccione una opcion-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);


                switch (option) {
                    case 0 -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tAl menu principal. ( ^_^)/\n");
                    }
                    case 1 -> {
                        System.out.println("\n");
                        System.out.print("\t\t\tIngrese la ip:");
                        String ip=scanner.next();
                        System.out.println("\n");
                        System.out.print("\t\t\t[2].ENVIAR SOLICITUD-> ");
                        String send=scanner.next();
                        if ("2".equals(send)) {
                            System.out.println("\n");
                            System.out.println("\t\t\t" + ip);
                            option=0;
                        }
                    }
                    default -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tIngrese un numero valido [1]\n");
                        System.out.println("\t\t\t[0]->Finalizar\n");
                        System.out.println("\n");
                        System.out.print("\t\t\tIngrese un caracter para continuar-> ");
                        scanner.next();
                    }
                }
            } while (option != 0);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

