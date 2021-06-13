package edu.utn.view;


import edu.utn.manager.GameManager;

import java.util.Scanner;

public class PrimaryStage extends Stage{
    @Override
    public void menu(GameManager manager) {

        try {

            int option;
            do
            {

                super.header();
                System.out.println("\n\t\t\t\tMAIN MENU");
                System.out.println("\n\t\t\t[1].START GAME");
                System.out.println("\t\t\t[0].QUIT GAME");
                System.out.print("\n\t\t\tSelect an option-> ");
                Scanner scanner =new Scanner(System.in);
                String number = scanner.next();
                option = Integer.parseInt(number);

                switch (option) {
                    case 0:
                        System.out.println("\n");
                        System.out.println("\t\t\tBye Ninja. ( ^_^)/\n");
                        super.footer();
                        break;
                    case 1:
                        manager.toServerRoom();
                        System.out.print("\t\t\tEnter to continue-> ");
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("\n");
                        System.out.println("\t\t\tEnter a valid number [1]");
                        System.out.println("\t\t\t[0]->Quit Game");
                        System.out.println("\n");
                        System.out.print("\t\t\tEnter to continue-> ");
                        scanner.nextLine();
                        break;
                }

            } while (option != 0);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
