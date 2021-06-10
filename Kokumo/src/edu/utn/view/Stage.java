package edu.utn.view;


import edu.utn.manager.GameManager;

public abstract class Stage implements View {


    @Override
    public void header() {
        System.out.println("\n     ======================================================================\n");
        System.out.println("\t\t     KOKUMO NO MONOGATARI (^o^)y\n");
        System.out.println("     ======================================================================\n");
    }

    public abstract void menu(GameManager manager);

    protected void print(GameManager manager){
        manager.printMessages();
        manager.clearMessages();
    }
    @Override
    public void footer() {

        System.out.println("\n     /**********************************************************************\n");
        System.out.println("\t\t\t*Student: Manuel Onetto Mouso\n");
        System.out.println("\t\t\t*Email: manumouso91@gmail.com\n");
        System.out.println("\t\t\t*Subjects: PROG/LAB III\n");
        System.out.println("\t\t\t*Career: TSSI-UTN-FRBA\n");
        System.out.println("\t\t\t*Date: 10/6/2021\n");
        System.out.println("     ***********************************************************************/\n");
    }


}
