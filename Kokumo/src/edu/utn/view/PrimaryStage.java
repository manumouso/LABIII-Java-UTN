package edu.utn.view;

import java.util.Scanner;

public class PrimaryStage extends Stage{
    @Override
    public void menu() {

        try {

            int option;
            do
            {

                super.header();
                System.out.println("\n\t\t\t\tMAIN MENU");
                System.out.println("\n\t\t\t[1].CREATE GAME");
                System.out.println("\t\t\t[2].JOIN GAME");
                System.out.println("\t\t\t[0].QUIT GAME");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0 -> {
                        System.out.println("\n");
                        System.out.println("\t\t\tBye Ninja. ( ^_^)/\n");
                        super.footer();
                    }
                    case 1 -> {
                        BoardView boardView = new BoardView();
                        boardView.printBoard();
                        boardView.advanceTest();
                        boardView.advanceTest2();
                        boardView.advanceTest3();
                        boardView.printMessages();
                        boardView.printMovementErrors();
                        boardView.cleanBoard();
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        JoinRoom joinRoom = new JoinRoom();
                        joinRoom.menu();
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
