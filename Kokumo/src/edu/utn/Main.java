package edu.utn;

import edu.utn.view.Introduction;
import edu.utn.view.PrimaryStage;

public class Main {

    public static void main(String[] args) {

        Introduction introduction= new Introduction();
        introduction.print();
        PrimaryStage primaryStage = new PrimaryStage();
        primaryStage.menu();


    }
}
