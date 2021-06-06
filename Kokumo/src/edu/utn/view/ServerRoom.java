package edu.utn.view;

import edu.utn.manager.GameManager;

import java.util.Scanner;

public class ServerRoom extends Stage{
    @Override
    public void menu(GameManager manager) {
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
                        GameManager gameManager = new GameManager();
                        MessagePrinter messagePrinter = new MessagePrinter();
                        BoardPrinter boardPrinter = new BoardPrinter();
                        boardPrinter.printAttackBoard();
                        //boardPrinter.printBoard(/*new Player()*/);

//                        boardView.advanceTest();
//                        boardView.advanceTest2();
//                        boardView.advanceTest3();
//                        boardView.advanceTest4();
//                        boardView.advanceTest5();
//                        boardView.advanceTest6();
                        //messagePrinter.printMessages(manager.getMessage().getMessageList());
                        boardPrinter.clearBoards(gameManager);
                        System.out.print("\t\t\tEnter a character to continue-> ");
                        scanner.next();

                    }
                    case 2 -> {
                        ClientRoom clientRoom = new ClientRoom();
                        clientRoom.menu(manager);
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

