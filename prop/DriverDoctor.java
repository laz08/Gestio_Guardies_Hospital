package prop;


import java.util.Scanner;
import java.lang.*;



public class DriverDoctor {

    private static Doctor doc = new Doctor(null);

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
                        arg = new Scanner(System.in);
                        dni = arg.next();
                        nom = arg.next();
                        cognom1 = arg.next();
                        cognom2 = arg.next();
                        sou = arg.nextInt();
                        telf = arg.nextInt();
                        correu = arg.next();

                        crearDoctor(dni, nom, cognom1, cognom2, sou, telf, correu);
                        break;

                    //Modificar nom
                    case 2:
                        arg = new Scanner(System.in);
                        nom = arg.next();
                        modificarNom(nom);
                        break;

                    case 3:
                        arg = new Scanner(System.in);
                        cognom1 = arg.next();
                        modificarCognom1(cognom1);
                        break;

                    case 4:
                        arg = new Scanner(System.in);
                        cognom2 = arg.next();
                        modificarCognom2(cognom2);
                        break;

                    //Modificar telf
                    case 5:
                        arg = new Scanner(System.in);
                        telf = arg.nextInt();
                        modificarTelf(telf);
                        break;


                    // Modificar correu
                    case 6:

                        arg = new Scanner(System.in);
                        correu = arg.toString();
                        modificarCorreu(correu);
                        break;


                    //Modificar sou
                    case 7:

                        arg = new Scanner(System.in);
                        sou = arg.nextInt();
                        modificarSou(sou);
                        break;

                    //Modificar Activitat
                    case 8:
                        arg = new Scanner(System.in);
                        actiu = arg.nextBoolean();
                        modificarActivitat(actiu);
                        break;

                    //Consultar dni
                    case 9:
                        consultarDni();
                        break;

                    //Consultar nom
                    case 10:
                        consultarNom();
                        break;

                    case 11:
                        consultarCognom1();
                        break;

                    case 12:
                        consultarCognom2();
                        break;

                    case 13:
                        consultarSou();
                        break;

                    case 14:
                       consultarTelefon();
                        break;

                    case 15:
                        consultarCorreu();
                        break;

                    case 16:
                        consultarActivitat();
                        break;

                    default:
                        System.out.println("El numero ha d'estar entre 0 i 16.");
                        break;

                }
            } catch (Exception e){
                System.err.println("Has d'introduir un numero\n"+e);
            }


        }
    }

    public static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.print("1.- Crear Doctor:");
        System.out.println("(dni:string, nom:string, cognom1:string, cognom2:string, sou:int, telf:int, correu:string)");
        System.out.println("---------------------------");
        System.out.println("2.- Modificar nom(nom: string)");
        System.out.println("3.- Modificar cognom1(cognom1: string)");
        System.out.println("4.- Modificar cognom2(cognom2: string)");
        System.out.println("5.- Modificar telf(telf:int)");
        System.out.println("6.- Modificar correu (correu:string)");
        System.out.println("7.- Modificar sou (sou:int)");
        System.out.println("8.- Modificar Activitat (actiu: boolean)");
        System.out.println("---------------------------");
        System.out.println("9.- Consultar DNI()");
        System.out.println("10.- Consultar nom ()");
        System.out.println("11.- Consultar cognom1 ()");
        System.out.println("12.- Consultar cognom2 ()");
        System.out.println("13.- Consultar sou ()");
        System.out.println("14.- Consultar telefon ()");
        System.out.println("15.- Consultar email ()");
        System.out.println("16.- Consultar activitat ()");
        System.out.println("---------------------------");
        System.out.println("0.- Exit");
        System.out.println("\n\n");
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

    public static void modificarNom(String nom){
        doc.setNom(nom);
    }

    public static void modificarCognom1(String cg1){
        doc.setCognom1(cg1);
    }


    public static void modificarCognom2(String cg2){
        doc.setNom(cg2);
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