package prop;

import java.util.Scanner;
import java.util.TreeSet;

public class DriverCtrlHospital {

    static Scanner arg = new Scanner(System.in);
    public static void main(String[] args) {

        int cas;
        boolean sortir = false;
        escriuMenu();
        while (!sortir && arg.hasNext()) {
            try {
                cas = arg.nextInt();
            } catch (Exception e){
                System.out.println("S'esperava la introduccio d'un numero.");
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

            if(cas != 0) escriuMenu();

        }
    }

    public static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.println("1.- crearDoctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("2.- Eliminar Doctor(dni: String)");
        System.out.println("3.- Existeix Doctor(dni: String)");
        System.out.println("4.- Consultar Doctor(dni: String)");
        System.out.println("5.- Consultar llistat doctors()");
        System.out.println("0.- Exit");
        System.out.print(">> ");
    }

    public static void missatgeErrorDni(){
        System.out.println("Ja existeix un doctor amb aquest DNI.");
        System.out.println("El llistat dels dni de doctors ja existents es el seguent: ");
        TreeSet<Doctor> ll = CtrlHospital.getHospital_dni();
        for (Doctor doc:ll)
            System.out.println(doc);

        System.out.println("Introdueix el DNI de nou d'aquest metge.");
        System.out.println("(Les altres dades es mantindran)");
        System.out.print(">>");

    }
    public static void casCreaDoctor(){

        boolean valid = false;

        String dni = arg.next();
        String nom = arg.next();
        String cg1 = arg.next();
        String cg2 = arg.next();
        int sou = 0;
        int telf = 0;

        valid = false;
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
        if (CtrlHospital.existeixDoctor(dni)){
            valid = false;
            missatgeErrorDni();
        }

        while(!valid) {
            dni = arg.next();
            if(CtrlHospital.existeixDoctor(dni)) valid = true;
            else{
                missatgeErrorDni();
            }
        }

        CtrlHospital.creariAfegirDoctor(dni, nom, cg1, cg2, sou, telf, correu);

    }

    public static void casEliminaDoctor(){
        String dni = arg.next();

        if(CtrlHospital.existeixDoctor(dni)){
            CtrlHospital.eliminarDoctor(dni);
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI.");
    }

    public static void casExisteixDoctor(){
        String dni = arg.next();
        if(CtrlHospital.existeixDoctor(dni))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public static void casConsultaDoctor(){
        String dni = arg.next();
        if (CtrlHospital.existeixDoctor(dni)){
            Doctor doc = CtrlHospital.getDoctor(dni);
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
         TreeSet<Doctor> ll = CtrlHospital.getHospital_nom();
        for(Doctor doc:ll) {
            System.out.println("\n\n");
            System.out.println("DNI: " + doc.getdni());
            System.out.println("nom i cognoms: " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2());
            System.out.println("sou: " + doc.getSou());
            System.out.println("Telefon: " + doc.getTelefon());
            System.out.println("Correu: " + doc.getCorreu());
        }
    }


}