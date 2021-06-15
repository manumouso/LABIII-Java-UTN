package edu.utn;


import edu.utn.factory.ManagerFactory;
import edu.utn.manager.GameManager;


public class Main {

    public static void main(String[] args) {

        ManagerFactory managerFactory = new ManagerFactory();
        GameManager manager =managerFactory.createManager();
        manager.startGame();

    }
}
