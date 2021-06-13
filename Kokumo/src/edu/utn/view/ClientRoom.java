package edu.utn.view;

import edu.utn.manager.GameManager;

import java.util.Scanner;

public class ClientRoom extends Stage{
    @Override
    public void menu(GameManager manager) {
//        try {
//
//            int option;
//            do
//            {
//
//                super.header();
//                System.out.println("\n\t\t\t\tJOIN GAME");
//                System.out.println("\n\t\t\t[1].ENTER IP");
//                System.out.println("\t\t\t[0].GO BACK");
//                System.out.print("\n\t\t\tSelect an option-> ");
//                Scanner scanner =new Scanner(System.in);
//                String number = scanner.next();
//                option = Integer.parseInt(number);
//
//
//                switch (option) {
//                    case 0 -> {
//                        System.out.println("\n");
//                        System.out.println("\t\t\tTo the main menu. ( ^_^)/\n");
//                    }
//                    case 1 -> {
//                        System.out.println("\n");
//                        System.out.print("\t\t\tEnter the IP address:");
//                        String ip=scanner.next();
//                        System.out.println("\n");
//                        System.out.print("\t\t\t[2].SEND REQUEST-> ");
//                        String send=scanner.next();
//                        if ("2".equals(send)) {
//                            System.out.println("\n");
//                            System.out.println("\t\t\t" + ip);
//                            option=0;
//                        }else{
//                            System.out.println("\t\t\tSend confirmation failed. Try again");
//                        }
//                    }
//                    default -> {
//                        System.out.println("\n");
//                        System.out.println("\t\t\tEnter a valid number [1]");
//                        System.out.println("\t\t\t[0]->Go Back");
//                        System.out.println("\n");
//                        System.out.print("\t\t\tEnter a character to continue-> ");
//                        scanner.next();
//                    }
//                }
//            } while (option != 0);
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
  }
}

