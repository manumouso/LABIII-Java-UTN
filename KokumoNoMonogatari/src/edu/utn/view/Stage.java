package edu.utn.view;



public abstract class Stage implements Menu {


    @Override
    public void header() {
        System.out.println("\n     ======================================================================\n");
        System.out.println("\t\t     KOKUMO NO MONOGATARI (^o^)y\n");
        System.out.println("     ======================================================================\n");
    }

    public abstract void menu();

    @Override
    public void footer() {

        System.out.println("\n     /**********************************************************************\n");
        System.out.println("\t\t\t*Alumno: Manuel Onetto Mouso\n");
        System.out.println("\t\t\t*Email: manumouso91@gmail.com\n");
        System.out.println("\t\t\t*Materias: PROG/LAB III\n");
        System.out.println("\t\t\t*Carrera: TSSI-UTN-FRBA\n");
        System.out.println("\t\t\t*Fecha: 7/5/2021\n");
        System.out.println("     ***********************************************************************/\n");
    }

    @Override
    public void clean(){
        try{
            String operatingSystem = System.getProperty("os.name");

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
