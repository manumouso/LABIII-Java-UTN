package edu.utn.view;

import edu.utn.Main;
import edu.utn.entity.AttackBoard;
import edu.utn.entity.Board;

import java.util.Scanner;

public class PrimaryStage extends Stage{


    public void menu() {

        try {

            int option;
            do
            {
                super.clean();
                super.header();
                System.out.println("\n\t\t\t\tMENU PRINCIPAL\n");
                System.out.println("\n\t\t\t[1].CREAR PARTIDA\n");
                System.out.println("\t\t\t[2].UNIRSE PARTIDA\n");
                System.out.println("\t\t\t[0].SALIR\n");
                System.out.print("\n\t\t\tSeleccione una opcion-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tAdios Ninja. ( ^_^)/\n");
                        super.footer();
                    }
                    case 1 -> {
                        BoardView boardView = new BoardView(new Board(),new AttackBoard());
                        boardView.printBoard(0,boardView.getBoard());
                        boardView.printBoard(0,boardView.getAttackBoard());
                        System.out.print("\t\t\tIngrese un caracter para continuar-> ");
                        scanner.next();

                    }
                    case 2 -> {

                        JoinRoom joinRoom = new JoinRoom();
                        joinRoom.menu();

                        System.out.print("\t\t\tIngrese un caracter para continuar-> ");
                        scanner.next();
                    }
                    default -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tIngrese un numero valido [1,2]\n");
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
