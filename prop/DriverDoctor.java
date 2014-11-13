package prop;


import java.util.Scanner;
import java.lang.*;



public class DriverDoctor {

    public static void main(String[] args) throws Error {

        Scanner arg;
        int cas;
        CtrlDoctor CtrlDoc = new CtrlDoctor();
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

            String dni;
            String nom;
            String cognom1;
            String cognom2;
            int sou;
            int telf;
            String correu;
            boolean actiu;

            switch(cas){


                    //Crear Doctor

                case 1:
                        //Tornem a llegir
                        Scanner arg2 = new Scanner(System.in);
                        dni = arg2.toString();
<<<<<<< HEAD

                        arg2 = new Scanner(System.in);
                        nom = arg2.toString();


                        arg2 = new Scanner(System.in);
                        cognom1 = arg2.toString();

                        arg2 = new Scanner(System.in);
                        cognom2 = arg2.toString();

                        arg2 = new Scanner(System.in);
                        sou = arg2.nextInt();

                        arg2 = new Scanner(System.in);
                        telf = arg2.nextInt();

=======
                        arg2 = new Scanner(System.in);
                        nom = arg2.toString();
                        arg2 = new Scanner(System.in);
                        cognom1 = arg2.toString();
                        arg2 = new Scanner(System.in);
                        cognom2 = arg2.toString();
                        arg2 = new Scanner(System.in);
                        sou = arg2.nextInt();
                        arg2 = new Scanner(System.in);
                        telf = arg2.nextInt();
>>>>>>> 866e95ac90529616f1234859b9902c1d518682d7
                        arg2 = new Scanner(System.in);
                        correu = arg2.toString();



                    doc = CtrlDoc.crearDoctor(dni, nom, cognom1, cognom2, sou, telf, correu);
                    break;


                //Modificar telf

                case 2:

                    arg2 = new Scanner(System.in);
                    telf = arg2.nextInt();
                    CtrlDoc.modificarTelf(doc, telf);

                    break;


                  // Modificar correu

                case 3:

                    arg2 = new Scanner(System.in);
                    correu = arg2.toString();
                    CtrlDoc.modificarCorreu(doc, correu);
                    break;


                //Modificar sou
                case 4:

                    arg2 = new Scanner(System.in);
                    sou = arg2.nextInt();
                    CtrlDoc.modificarSou(doc, sou);
                    break;

                    //Modificar Activitat

                case 5:
                    arg2 = new Scanner(System.in);
                    actiu = arg2.nextBoolean();
                    CtrlDoc.modificarActivitat(doc, actiu);
                    break;

                //Consultar dni
                case 6:
                    dni = CtrlDoc.consultarDni(doc);
                    System.out.println(dni);
                    break;

                //Consultar nom
                case 7:
                    nom = CtrlDoc.consultarNom(doc);
                    System.out.println(nom);
                    break;

                case 8:
                    String cog1 = CtrlDoc.consultarCognom1(doc);
                    System.out.println(cog1);
                    break;

                case 9:
                    String cog2 = CtrlDoc.consultarCognom2(doc);
                    System.out.println(cog2);
                    break;

                case 10:
                    sou = CtrlDoc.consultarSou(doc);
                    System.out.println(sou);
                    break;

                case 11:
                    telf = CtrlDoc.consultarTelefon(doc);
                    System.out.println(telf);
                    break;

                case 12:
                    String email = CtrlDoc.consultarCorreu(doc);
                    System.out.println(email);
                    break;

                case 13:
                    actiu = CtrlDoc.consultarActivitat(doc);
                    System.out.println(actiu);
                    break;

                default:
                    System.out.println("El numero ha d'estar entre 1 i 13.");
                    break;

            }


        }
    }
}