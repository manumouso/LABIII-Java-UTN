package edu.utn;

import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

public class Main {

    public static void main(String[] args) {

        Introduction.print();
        PrimaryStage primaryStage = new PrimaryStage();
        primaryStage.menu();


    }
}
