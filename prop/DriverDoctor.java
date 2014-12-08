package prop;


import java.util.Scanner;
import java.lang.*;



public class DriverDoctor {

    private static Doctor doc = null;
    static Scanner arg = new Scanner(System.in);

    public static void main(String[] args){


        int cas;

        boolean sortir = false;
        escriuMenu();

        while (!sortir && arg.hasNext()) {
            try {
                cas = arg.nextInt();
            } catch (Exception e){
                System.out.println("S'esperava la introducció d'un número.");
                System.out.print(">> ");
                arg.next();
                continue;
            }
            switch (cas) {
                case 0: sortir = true; break;
                case 1: casCreaDoctor(); break;
                case 2: casModificaNom(); break;
                case 3: casModificaCognom1(); break;
                case 4: casModificaCognom2(); break;
                case 5: casModificaTelf(); break;
                case 6: casModificaCorreu(); break;
                case 7: casModificaSou(); break;
                case 8: casModificaActivitat(); break;
                case 9: consultarDni(); break;
                case 10: consultarNom(); break;
                case 11: consultarCognom1();break;
                case 12: consultarCognom2(); break;
                case 13: consultarSou(); break;
                case 14: consultarTelefon(); break;
                case 15: consultarCorreu(); break;
                case 16: consultarActivitat(); break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 16.");
                    break;
            }
            escriuMenu();
        }
    }

    public static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.print("1.- Crear Doctor");
        System.out.println("(dni: string, nom: string, cognom1: string, cognom2: string, sou: int, telf: int, correu: string)");
        System.out.println("2.- Modificar nom(nom: string)");
        System.out.println("3.- Modificar cognom1(cognom1: string)");
        System.out.println("4.- Modificar cognom2(cognom2: string)");
        System.out.println("5.- Modificar telf(telf:int)");
        System.out.println("6.- Modificar correu (correu:string)");
        System.out.println("7.- Modificar sou (sou:int)");
        System.out.println("8.- Modificar Activitat (actiu: boolean)");
        System.out.println("9.- Consultar DNI()");
        System.out.println("10.- Consultar nom ()");
        System.out.println("11.- Consultar cognom1 ()");
        System.out.println("12.- Consultar cognom2 ()");
        System.out.println("13.- Consultar sou ()");
        System.out.println("14.- Consultar telefon ()");
        System.out.println("15.- Consultar email ()");
        System.out.println("16.- Consultar activitat ()");
        System.out.println("0.- Exit");
        System.out.println("---------------------------");
        System.out.print(">> ");
    }

    public static void casCreaDoctor() {
        arg = new Scanner(System.in);
        String dni = arg.next();
        String nom = arg.next();
        String cg1 = arg.next();
        String cg2 = arg.next();
        int sou = 0;
        int telf = 0;

        boolean valid = false;
        while(!valid) {
            try {
                sou = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Sou ha de ser un enter");
                arg.next();
                continue;
            }
        }
        valid = false;
        while(!valid){
            try {
                telf = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Telefon ha de ser un enter");
                arg.next();
                continue;
            }
        }
        String correu = arg.next();
        crearDoctor(dni, nom, cg1, cg2, sou, telf, correu);
    }
    public static void crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        if(doc == null) {
            doc = new Doctor(d);
            doc.setNom(n);
            doc.setCognom1(cg1);
            doc.setCognom2(cg2);
            doc.setSou(s);
            doc.setTelefon(telf);
            doc.setCorreu(mail);
            doc.setActiu(false); //Primer el posem com a inactiu
        }
        else
            System.out.println("Ja tenim un doctor creat, no en pots crear un altre de nou.");
    }

    public static void casModificaNom() {
        arg = new Scanner(System.in);
        String nom = arg.next();
        modificarNom(nom);
    }
    public static void modificarNom(String nom){
        if (doc != null)
            doc.setNom(nom);
        else
            System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaCognom1() {
        arg = new Scanner(System.in);
        String cognom1 = arg.next();
        modificarCognom1(cognom1);
    }
    public static void modificarCognom1(String cg1){
        if(doc != null)
            doc.setCognom1(cg1);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaCognom2() {
        arg = new Scanner(System.in);
        String cognom2 = arg.next();
        modificarCognom2(cognom2);
    }
    public static void modificarCognom2(String cg2){
        if (doc != null)
            doc.setNom(cg2);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaTelf() {
        arg = new Scanner(System.in);
        int telf = -1;
        boolean valid = false;
        while(!valid) {
            try {
                telf = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Telf ha de ser un enter");
                System.out.println("Torna a introduir el telèfon.");
                arg.next();
                continue;
            }
        }
        modificarTelf(telf);
    }
    public static void modificarTelf(int telf){
        if (doc != null)
            doc.setTelefon(telf);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaCorreu() {
        arg = new Scanner(System.in);
        String correu = arg.toString();
        modificarCorreu(correu);
    }
    public static void modificarCorreu(String mail){
        if (doc != null)
            doc.setCorreu(mail);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaSou() {
        arg = new Scanner(System.in);
        int sou = -1;
        boolean valid = false;
        while(!valid) {
            try {
                sou = arg.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Sou ha de ser un enter");
                System.out.println("Torna a introduir el sou.");
                arg.next();
                continue;
            }
        }
        modificarSou(sou);
    }
    public static void modificarSou(int sou){
        if (doc != null)
            doc.setSou(sou);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }

    public static void casModificaActivitat() {
        arg = new Scanner(System.in);
        boolean actiu = true;
        boolean valid = false;
        while(!valid) {
            try {
                actiu = arg.nextBoolean();
                valid = true;
            } catch (Exception e) {
                System.out.println("Actiu ha de ser un booleà");
                System.out.println("Torna a introduir l'activitat (Escriu true o false.)");
                arg.next();
                continue;
            }
        }
        modificarActivitat(actiu);
    }
    public static void modificarActivitat(boolean actiu){
        if (doc != null)
            doc.setActiu(actiu);
        else System.out.println("Primer has de crear un doctor per poder modificar les seves dades.");
    }


    public static void consultarNom(){
        if(doc != null)
            System.out.println(doc.getNom());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarCognom1(){
        if (doc != null)
            System.out.println(doc.getCognom1());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarCognom2(){
        if(doc != null)
            System.out.println(doc.getCognom2());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarDni(){
        if (doc != null)
            System.out.println(doc.getdni());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarSou(){
        if(doc != null)
            System.out.println(doc.getSou());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarTelefon(){
        if (doc != null)
            System.out.println(doc.getTelefon());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarCorreu(){
        if (doc != null)
        System.out.println(doc.getCorreu());
        else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }

    public static void consultarActivitat(){
       if (doc != null)
           System.out.println(doc.isActiu());
       else System.out.println("Primer has de crear un doctor per poder consultar les seves dades.");
    }
}