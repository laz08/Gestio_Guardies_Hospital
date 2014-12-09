package prop;

import java.util.Scanner;
import java.util.TreeSet;

public class DriverHospital {


    static Scanner arg = new Scanner(System.in);
    public static void main(String[] args) {

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
                case 1: casCreaDoctor(); break;
                case 2: casEliminaDoctor(); break;
                case 3: casExisteixDoctor(); break;
                case 4: casConsultaDoctor(); break;
                case 5: casLlistatDoctors(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 5.");
                    break;
            }
            if (cas != 0) escriuMenu();
        }
    }

    private static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.println("1.- crearDoctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("2.- Eliminar Doctor(dni: String)");
        System.out.println("3.- Existeix Doctor(dni: String)");
        System.out.println("4.- Consultar Doctor(dni: String)");
        System.out.println("5.- Consultar llistat doctors()");
        System.out.println("0.- Exit");
        System.out.println("---------------------------");
        System.out.print(">> ");
    }

    private static void missatgeErrorDni(){
        System.out.println("Ja existeix un doctor amb aquest DNI.");
        System.out.println("El llistat dels dni de doctors ja existents es el seguent: ");
        TreeSet<Doctor> ll = Hospital.getHospital_dni();
        for (Doctor doc:ll)
            System.out.println(doc.getdni());

        System.out.println("Introdueix el DNI de nou d'aquest metge.");
        System.out.println("(Les altres dades es mantindran)");
        System.out.print(">>");

    }

    private static void creariAfegirDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu

        //S'ordena sol
        //Afegim a l'arbre de DNI
        Hospital.getHospital_dni().add(doc);

        //Afegim a l'arbre de Nom
        Hospital.getHospital_nom().add(doc);
    }

    private static boolean existeixDoctor(String dni){
        //Creem un Dummy i comprovem si existeix ja aquest doctor
        Doctor d = new Doctor(dni);
        return Hospital.getHospital_dni().contains(d);
    }

    private static Doctor getDoctor(String dni){
        return Hospital.getHospital_dni().ceiling(new Doctor(dni));
    }

    public static void casCreaDoctor(){
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

        if(existeixDoctor(dni))
            valid = false;

        while(!valid){
            missatgeErrorDni();
            dni = arg.next();
            if(!existeixDoctor(dni)) valid = true;
        }

        creariAfegirDoctor(dni, nom, cg1, cg2, sou, telf, correu);

    }

    public static void casEliminaDoctor(){
        arg = new Scanner(System.in);
        String dni = arg.next();
        if(existeixDoctor(dni)){
            Doctor doc = getDoctor(dni);
            Hospital.getHospital_dni().remove(doc);
            Hospital.getHospital_nom().remove(doc);
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI.");
    }

    public static void casExisteixDoctor(){
        arg = new Scanner(System.in);
        String dni = arg.next();
        if(existeixDoctor(dni))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public static void casConsultaDoctor(){
        arg = new Scanner(System.in);
        String dni = arg.next();
        if (existeixDoctor(dni)){
            Doctor doc = getDoctor(dni);
            System.out.println("dni: "+doc.getdni());
            System.out.println("nom i cognoms: "+doc.getNom()+" "+doc.getCognom1()+" "+doc.getCognom2());
            System.out.println("sou: "+doc.getSou());
            System.out.println("Telefon: "+doc.getTelefon());
            System.out.println("Correu: "+doc.getCorreu());
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI.");
    }

    public static void casLlistatDoctors(){
        //Agafem l'hospital alfabèticament
        TreeSet<Doctor> ll = Hospital.getHospital_nom();
        for(Doctor doc:ll) {
            System.out.println("\n");
            System.out.println("DNI: " + doc.getdni());
            System.out.println("nom i cognoms: " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2());
            System.out.println("sou: " + doc.getSou());
            System.out.println("Telefon: " + doc.getTelefon());
            System.out.println("Correu: " + doc.getCorreu());
        }
    }


}