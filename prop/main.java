package prop;

import java.util.Scanner;

/**
 * Main per consola
 * (2a entrega)
 */
public class main {

    static Scanner arg = new Scanner(System.in);

    public static void main(String[] args){
        int cas = 0;
        boolean sortir = false;
        escriuMenu();

        while (!sortir && arg.hasNext()){
            try {
                cas = arg.nextInt();
            } catch (Exception e){
                System.out.println("S'esperava la introducció d'un número.");
                System.out.print(">> ");
                arg.next();
                continue;
            }

            switch(cas){
                case 1: casGestioDoctors(); break;
                case 2: casGestioPlantilles(); break;
                case 3: casGestioRestriccions(); break;
                case 4: casAplicarAlgorisme(); break;
                case 5: casGuardar(); break;
                case 6: casCarregar(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 6.");
                    break;
            }

            if (cas != 0) escriuMenu();
        }
    }

    private static void escriuMenu(){
        System.out.println("----------MENU PRINCIPAL----------");
        System.out.println("1.- Gestió de doctors (Hospital)");
        System.out.println("2.- Gestió de plantilles");
        System.out.println("3.- Gestió de Restriccions");
        System.out.println("4.- Escollir algorisme a aplicar");
        System.out.println("5.- Guardar");
        System.out.println("6.- Carregar");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }


    private static void escriuMenuGestioDoctors(){

    }
    private static void casGestioDoctors(){
        escriuMenuGestioDoctors();
    }

    private static void escriuMenuGestioPlantilles(){

    }
    private static void casGestioPlantilles(){
        escriuMenuGestioPlantilles();
    }

    private static void escriuMenuGestioRestriccions(){

    }
    private static void casGestioRestriccions(){
        escriuMenuGestioRestriccions();
    }

    
}
