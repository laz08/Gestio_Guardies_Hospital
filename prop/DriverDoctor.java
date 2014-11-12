package prop;

import java.lang.NumberFormatException;
import java.util.Scanner;
import java.lang.*;

import prop.*;

public class DriverDoctor {

    public static void main(String[] args) {

        Scanner arg;
        int cas;
        while (true) {
            System.out.println("Menú");
            System.out.println("1.- Crear Doctor:");
            System.out.println("dni, nom, cognom1, cognom2, sou, telf, correu");
            System.out.println("2.- Modificar telf (telf)");
            System.out.println("3.- Modificar correu (correu)");
            System.out.println("4.- Modificar sou (sou)");
            System.out.println("5.- Modificar Activitat (actiu)");
            System.out.println("6.- Consultar DNI ()");
            System.out.println("7.- Consultar nom ()");
            System.out.println("8.- Consultar cognom1 ()");
            System.out.println("9.- Consultar cognom2 ()");
            System.out.println("10.- Consultar sou ()");
            System.out.println("11.- Consultar telefon ()");
            System.out.println("12.- Consultar email ()");
            System.out.println("13.- Consultar activitat ()");


            Doctor doc = new Doctor();

            arg = new Scanner(System.in);
            try {
                cas = arg.nextInt();
            } catch (NumberFormatException e){
                throw new Error ("Has d'introduir un numero.");
            }

            switch(cas){


                    //Crear Doctor

                case 1:
                        //Tornem a llegir
                        Scanner arg2 = new Scanner(System.in);
                        String dni = arg2.toString();
                        arg2 = new Scanner(System.in);
                        String nom = arg2.toString();
                        arg2 = new Scanner(System.in);
                        String cognom1 = arg2.toString();
                        arg2 = new Scanner(System.in);
                        String cognom2 = arg2.toString();
                        arg2 = new Scanner(System.in);
                        int sou = arg2.nextInt();
                        arg2 = new Scanner(System.in);
                        int telf = arg2.nextInt();
                        arg2 = new Scanner(System.in);
                        String correu = arg.toString();


                        doc = CtrlDoctor.crearDoctor(dni, nom, cognom1, cognom2, sou, telf, correu);
                    break;


                //Modificar telf

                case 2:

                    Scanner arg2 = new Scanner(System.in);
                    int telf = arg2.nextInt();
                    CtrlDoctor.modificarTelf(doc, telf);
                    break;


                  // Modificar correu

                case 3:

                    Scanner arg2 = new Scanner(System.in);
                    String correu = arg2.toString();
                    CtrlDoctor.modificarCorreu(doc, correu);
                    break;


                //Modificar sou
                case 4:

                    Scanner arg2 = new Scanner(System.in);
                    String sou = arg2.nextInt();
                    CtrlDoctor.modificarSou(doc, sou);
                    break;

                    //Modificar Activitat

                case 5:
                    Scanner arg2 = new Scanner(System.in);
                    boolean actiu = arg2.nextBoolean();
                    CtrlDoctor.modificarActivitat(doc, actiu);
                    break;

                //Consultar dni
                case 6:
                    String dni = CtrlDoctor.consultarDni(doc);
                    System.out.println(dni);
                    break;

                //Consultar nom
                case 7:
                    String nom = CtrlDoctor.consultarNom(doc);
                    System.out.println(nom);
                    break;

                case 8:
                    String cog1 = CtrlDoctor.consultarCognom1(doc);
                    System.out.println(cog1);
                    break;

                case 9:
                    String cog2 = CtrlDoctor.ConsultarCognom2(doc);
                    System.out.println(cog2);
                    break;

                case 10:
                    int sou = CtrlDoctor.consultarSou(doc);
                    System.out.println(sou);
                    break;

                case 11:
                    int telf = CtrlDoctor.consultarTelefon(doc);
                    System.out.println(telf);
                    break;

                case 12:
                    String email = CtrlDoctor.consultarCorreu(doc);
                    System.out.println(email);
                    break;

                case 13:
                    boolean actiu = CtrlDoctor.consultarActivitat(doc);
                    System.out.println(actiu);
                    break;
                default:
                    System.out.println("El numero ha d'estar entre 1 i 13.");
                    break;

            }


        }
    }
}