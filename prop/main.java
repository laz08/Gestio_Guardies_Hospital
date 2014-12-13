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

    private static void lecturaTeclatGestioDoctors(){

    }

    private static void lecturaTeclatGestioPlantilles(){

    }

    private static void lecturaTeclatGestioRestriccions(){

    }

    private static void lecturaTeclatAplicarAlgorisme(){

    }

    private static void lecturaTeclatGuardar(){

    }

    private static void lecturaTeclatCarregar(){

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
        System.out.println("----------GESTIÓ DE DOCTORS DE L'HOSPITAL----------");
        System.out.println("1.- Donar d'alta Doctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("2.- Eliminar Doctor(dni: String)");
        System.out.println("3.- Existeix Doctor(dni: String)");
        System.out.println("4.- Consultar Doctor(dni: String)");
        System.out.println("5.- Consultar llistat doctors()");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioDoctors(){
        escriuMenuGestioDoctors();
        lecturaTeclatGestioDoctors();
        //Crida a CtrlDomini pel cas d'us escollit
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

    private static void escriuMenuAplicarAlgorisme(){

    }
    private static void casAplicarAlgorisme(){
        escriuMenuAplicarAlgorisme();
    }

    private static void escriuMenuGuardar(){

    }
    private static void casGuardar(){
        escriuMenuGuardar();
    }

    private static void escriuMenuCarregar(){

    }
    private static void casCarregar(){
        escriuMenuCarregar();
    }
}
