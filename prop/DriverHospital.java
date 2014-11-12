package prop;

import java.lang.NumberFormatException;
import java.util.Scanner;

public class DriverHospital {

    public static void main(String[] args) {

        Hospital H;
        Scanner arg;
        int cas;
        while (true) {
            System.out.println("Menú");
            System.out.println("1.- Afegir Doctor:");
            System.out.println("dni, nom, cognom1, cognom2, sou, telf, correu");

            System.out.println("2.- Eliminar Doctor");
            System.out.println("dni");
            System.out.println("3.- Existeix Doctor:");
            System.out.println("dni");
            System.out.println("4.- Consultar Doctor");
            System.out.println("dni");

            arg = new Scanner(System.in);
            try {
                cas = arg.parseInt();
            } catch (NumberFormatException e){
                throw new Error ("Has d'introduir un numero.");
            }

            switch(cas){
                case 1:
                        //Tornem a llegir
                        String dni = new Scanner(System.in);
                        String nom = new Scanner(System.in);
                        String cognom1 = new Scanner(System.in);
                        String cognom2 = new Scanner(System.in);
                        String lsou = new Scanner(System.in);
                        int sou = lsou.nextInt();
                        String ltelf = new Scanner(System.in);
                        int telf = ltelf.nextInt();
                        String correu = new Scanner(System.in);



                    break;

                case 2:

                    break;

                case 3:
                    break;

                case 4:
                    break;

                default:
                    System.out.println("El numero ha d'estar entre 1 i 4.");
                    break;
            }


        }
    }
}