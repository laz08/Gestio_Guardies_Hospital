package prop;


import java.util.Scanner;
import java.lang.*;



public class DriverDoctor {

    static Doctor doc = new Doctor();

    public static void main(String[] args) throws Error {

        Scanner arg;
        int cas;

        String dni;
        String nom;
        String cognom1;
        String cognom2;
        int sou;
        int telf;
        String correu;
        boolean actiu;

        //CtrlDoctor CtrlDoc = new CtrlDoctor();


        boolean sortir = false;

        while (!sortir) {
            try {

                escriuMenu();

                arg = new Scanner(System.in);
                cas = arg.nextInt();


                switch (cas) {
                    case 0:
                        sortir = true;
                        break;

                    //Crear Doctor

                    case 1:
                        Scanner arg2 = new Scanner(System.in);
                        dni = arg2.next();
                        nom = arg2.next();
                        cognom1 = arg2.next();
                        cognom2 = arg2.next();
                        sou = arg2.nextInt();
                        telf = arg2.nextInt();
                        correu = arg2.next();

                        crearDoctor(dni, nom, cognom1, cognom2, sou, telf, correu);
                        break;


                    //Modificar telf
                    case 2:
                        arg2 = new Scanner(System.in);
                        telf = arg2.nextInt();
                        modificarTelf(telf);
                        break;


                    // Modificar correu

                    case 3:

                        arg2 = new Scanner(System.in);
                        correu = arg2.toString();
                        modificarCorreu(correu);
                        break;


                    //Modificar sou
                    case 4:

                        arg2 = new Scanner(System.in);
                        sou = arg2.nextInt();
                        modificarSou(sou);
                        break;

                    //Modificar Activitat

                    case 5:
                        arg2 = new Scanner(System.in);
                        actiu = arg2.nextBoolean();
                        modificarActivitat(actiu);
                        break;

                    //Consultar dni
                    case 6:
                        consultarDni();
                        break;

                    //Consultar nom
                    case 7:
                        consultarNom();
                        break;

                    case 8:
                        consultarCognom1();
                        break;

                    case 9:
                        consultarCognom2();
                        break;

                    case 10:
                        consultarSou();
                        break;

                    case 11:
                       consultarTelefon();
                        break;

                    case 12:
                        consultarCorreu();
                        break;

                    case 13:
                        consultarActivitat();
                        break;

                    default:
                        System.out.println("El numero ha d'estar entre 0 i 13.");
                        break;

                }
            } catch (Exception e){
                System.err.println("Has d'introduir un numero\n"+e);
            }


        }
    }

    public static void escriuMenu(){
        System.out.println("\n\nMenú");
        System.out.print("1.- Crear Doctor:");
        System.out.println("(dni:string, nom:string, cognom1:string, cognom2:string, sou:int, telf:int, correu:string)");
        System.out.println("2.- Modificar telf(telf:int)");
        System.out.println("3.- Modificar correu (correu:string)");
        System.out.println("4.- Modificar sou (sou:int)");
        System.out.println("5.- Modificar Activitat (actiu: boolean)");
        System.out.println("6.- Consultar DNI()");
        System.out.println("7.- Consultar nom ()");
        System.out.println("8.- Consultar cognom1 ()");
        System.out.println("9.- Consultar cognom2 ()");
        System.out.println("10.- Consultar sou ()");
        System.out.println("11.- Consultar telefon ()");
        System.out.println("12.- Consultar email ()");
        System.out.println("13.- Consultar activitat ()");
        System.out.println("0.- Exit");
    }

    public static void crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        doc.setdni(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu
    }


    public static void modificarTelf(int telf){
        doc.setTelefon(telf);
    }

    public static void modificarCorreu(String mail){
        doc.setCorreu(mail);
    }

    public static void modificarSou(int sou){
        doc.setSou(sou);
    }

    public static void modificarActivitat(boolean actiu){
        doc.setActiu(actiu);
    }

    public static void consultarNom(){
        System.out.println(doc.getNom());
    }

    public static void consultarCognom1(){
        System.out.println(doc.getCognom1());

    }

    public static void consultarCognom2(){
        System.out.println(doc.getCognom2());
    }

    public static void consultarDni(){
        System.out.println(doc.getdni());
    }

    public static void consultarSou(){
        System.out.println(doc.getSou());
    }

    public static void consultarTelefon(){
        System.out.println(doc.getTelefon());
    }

    public static void consultarCorreu(){
        System.out.println(doc.getCorreu());
    }

    public static void consultarActivitat(){
       System.out.println(doc.isActiu());
    }


}