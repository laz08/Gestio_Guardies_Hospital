/*
package prop;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Main per consola
 * (2a entrega)
 
public class main {

    static Scanner arg = new Scanner(System.in);

    public static void main(String[] args) throws Error{
        int cas = 0;
        boolean sortir = false;
        escriuMenuPrincipal();

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
                case 3: casGestioCalendaris(); break;
                case 4: casGestioRestriccions(); break;
                case 5: casAplicarAlgorisme(); break;
                case 6: casGuardar(); break;
                case 7: casCarregar(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 6.");
                    break;
            }

            if (cas != 0) escriuMenuPrincipal();
        }
    }

    private static void escriuMenuPrincipal(){
        System.out.println("----------MENU PRINCIPAL----------");
        System.out.println("1.- Gestió de doctors (Hospital)");
        System.out.println("2.- Gestió de plantilles");
        System.out.println("3.- Gestió de Calendaris");
        System.out.println("4.- Gestió de Restriccions");
        System.out.println("5.- Escollir algorisme a aplicar");
        System.out.println("6.- Guardar");
        System.out.println("7.- Carregar");
        System.out.println("0.- Sortir");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }


    private static int lecturaTeclat(){
        int menu = 0;
            try {
                menu = arg.nextInt();
            } catch (Exception e){
                System.out.println("S'esperava la introducció d'un número.");
                System.out.print(">> ");
                arg.next();
            }
        return menu;
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------HOSPITAL---------------------
    //----------------------------------------------------

    //-----------------------Auxiliars--------------------
    public static int lecturaTelf(){
        boolean valid = false;
        int telf = 0;
        while(!valid){
            try{
                telf = arg.nextInt();
                if (telf >= 0) valid = true;
                else{
                    System.out.println("Telèfon ha de ser un nombre positiu.");
                    System.out.print("Torna a introduir el telèfon: ");
                }
            } catch (Exception e){
                System.out.println("Telèfon ha de ser un nombre positiu.");
                System.out.print("Torna a introduir el telèfon: ");
                arg.next();
                continue;
            }
        }
        return telf;
    }

    public static int lecturaSou(){
        boolean valid = false;
        int sou = 0;
        while(!valid){
            try{
                sou = arg.nextInt();
                if (sou >= 0) valid = true;
                else{
                    System.out.println("Sou ha de ser un nombre positiu.");
                    System.out.print("Torna a introduir el sou: ");
                }
            } catch (Exception e){
                System.out.println("Sou ha de ser un nombre positiu.");
                System.out.print("Torna a introduir el sou: ");
                arg.next();
                continue;
            }
        }
        return sou;
    }

    public static void missatgeErrorDni(){
        System.out.println("Ja existeix un doctor amb aquest DNI.");
        System.out.println("El llistat dels dni de doctors ja existents es el seguent: ");
        TreeSet<Doctor> ll = CtrlDomini.consultaHospital_dni();
        for (Doctor doc:ll)
            System.out.println(doc.getdni());

        System.out.println("Introdueix el DNI de nou d'aquest metge.");
        System.out.println("(Les altres dades es mantindran)");
        System.out.print(">>");

    }
    private static void casCrearDoctor(){
        String dni = arg.next();
        String nom = arg.next();
        String cg1 = arg.next();
        String cg2 = arg.next();
        int sou = lecturaSou();
        int telf = lecturaTelf();
        String correu = arg.next();
        boolean valid = false;
        /*
        Comprovem si existeix doctor amb aquest DNI
         
        if (!CtrlDomini.existeixDoctoraHospital(dni)){
            valid = true;
        }

        while(!valid){
            missatgeErrorDni();
            dni = arg.next();
            if(!CtrlDomini.existeixDoctoraHospital(dni)) valid = true;
        }

        CtrlDomini.creaDoctor(dni, nom, cg1, cg2, sou, telf, correu);
    }
    private static void modificarNom(Doctor doc){
        String nom = arg.next();
        doc.setNom(nom);
    }
    private static void modificarCognom1(Doctor doc){
        String cognom = arg.next();
        doc.setCognom1(cognom);
    }
    private static void modificarCognom2(Doctor doc){
        String cognom = arg.next();
        doc.setCognom2(cognom);
    }
    private static void modificarCorreu(Doctor doc){
        String correu = arg.next();
        doc.setCorreu(correu);
    }
    private static void modificarTelefon(Doctor doc){
        int telf = lecturaTelf();
        doc.setTelefon(telf);
    }
    private static void modificarSou(Doctor doc){
       int sou = lecturaSou();
        doc.setSou(sou);
    }
    //------------FALTA!!!!---------------
    private static void afegirRestriccions(Doctor doc){}
    private static void esborrarRestriccions(Doctor doc){}
    //------------!!!!!!!!!---------------

    private static void casModificaDoctor(){
        String dni = arg.next();
        if(CtrlDomini.existeixDoctoraHospital(dni)) {
            Doctor doc = CtrlDomini.consultaDoctor(dni);
            //Es pot modificar qualsevol cosa menys el DNI!
            escriuMenuModificaDoctors(dni);
            boolean sortir = false;
            while (!sortir) {
                int menu = lecturaTeclat();
                switch (menu) {
                    case 1:
                        modificarNom(doc);
                        break;
                    case 2:
                        modificarCognom1(doc);
                        break;
                    case 3:
                        modificarCognom2(doc);
                        break;
                    case 4:
                        modificarTelefon(doc);
                        break;
                    case 5:
                        modificarSou(doc);
                        break;
                    case 6:
                        modificarCorreu(doc);
                        break;
                    case 7:
                        afegirRestriccions(doc);
                        break;
                    case 8:
                        esborrarRestriccions(doc);
                        break;
                    case 0:
                        sortir = true;
                        break;
                    default:
                        System.out.println("El numero ha d'estar entre 0 i 8.");
                        break;
                }
                if (menu != 0) escriuMenuModificaDoctors(dni);
            }
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI");
    }
    private static void casConsultaDoctorHospital(){
        String dni = arg.next();
        if(CtrlDomini.existeixDoctoraHospital(dni)){
            Doctor doc = CtrlDomini.consultaDoctor(dni);
            System.out.println("DNI: "+doc.getdni());
            System.out.println("Nom i cognoms: "+doc.getNom()+" "+doc.getCognom1()+" "+doc.getCognom2());
            System.out.println("Sou: "+doc.getSou());
            System.out.println("Telefon: "+doc.getTelefon());
            System.out.println("Correu: "+doc.getCorreu());
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI.");
    }
    private static void casEliminaDoctor(){
        String dni = arg.next();
        if(CtrlDomini.existeixDoctoraHospital(dni)){
                CtrlDomini.eliminaDoctorHospital(dni);
        }
        else
            System.out.println("No existeix cap doctor amb aquest DNI.");
    }
    private static void casCarregarDoctors(){
        CtrlDomini.carregarDoctors();
    }
    private static void casGuardarDoctors(){
        CtrlDomini.guardarDoctors();
    }
    private static void casConsultaLlistatDoctors(){
        //Els traiem per pantalla per ordre alfabètic
        TreeSet<Doctor> ll = CtrlDomini.consultaHospital_nom();
        System.out.println("Núm. de doctors en total: "+ ll.size());
        for(Doctor doc:ll) {
            System.out.println("DNI: " + doc.getdni());
            System.out.println("Cognoms, Nom: " + doc.getCognom1() + " " + doc.getCognom2() + ", " + doc.getNom());
            System.out.println("Sou: " + doc.getSou());
            System.out.println("Telefon: " + doc.getTelefon());
            System.out.println("Correu: " + doc.getCorreu());
            System.out.println("\n");
        }
    }

    //-----------------------Principal--------------------
    private static void escriuMenuModificaDoctors(String dni){
        System.out.println("----------Modificar doctor amb dni "+dni+"----------");
        System.out.println("1.- Modificar nom (nom: String)");
        System.out.println("2.- Modificar cognom1 (cognom1: String)");
        System.out.println("3.- Modificar cognom2 (cognom2: String)");
        System.out.println("4.- Modificar telefon (telf: int)");
        System.out.println("5.- Modificar sou (sou: int)");
        System.out.println("6.- Modificar correu (mail: String)");
        System.out.println("7.- Afegir restriccions");
        System.out.println("8.- Esborrar restriccions");
        System.out.println("0.- Tornar a Gestió de Doctors de l'Hospital");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void escriuMenuGestioDoctors(){
        System.out.println("----------GESTIÓ DE DOCTORS DE L'HOSPITAL----------");
        System.out.println("1.- Donar d'alta Doctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("2.- Modificar Doctor(dni: String)");    //Crida a menuModificaDoctor
        System.out.println("3.- Consultar Doctor(dni: String)");
        System.out.println("4.- Eliminar Doctor(dni: String)");
        System.out.println("5.- Carregar Doctors()");
        System.out.println("6.- Guardar Doctors()");
        System.out.println("7.- Consultar llistat doctors()");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioDoctors(){
        escriuMenuGestioDoctors();
        boolean sortir = false;
        while (!sortir) {
            int menu = lecturaTeclat();
            switch (menu) {
                case 1: casCrearDoctor(); break;
                case 2: casModificaDoctor(); break;
                case 3: casConsultaDoctorHospital();  break;
                case 4: casEliminaDoctor(); break;
                case 5: casCarregarDoctors(); break;
                case 6: casGuardarDoctors(); break;
                case 7: casConsultaLlistatDoctors(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 7.");
                    break;
            }
            if (menu != 0) escriuMenuGestioDoctors();
        }
    }


    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------PLANTILLA---------------------
    //----------------------------------------------------

    //-----------------------Auxiliars--------------------
    public static void casCreaPlantilla(){
        String nom_p = arg.next();
        if(!CtrlDomini.existeixPlantilla(nom_p)){
            CtrlDomini.crearPlantilla(nom_p);
        }
        else
            System.out.println("Ja existeix plantilla amb nom " + nom_p);

    }
    public static void modificaNomPlantilla(Plantilla p){
        String nom = arg.next();
        p.setNomPlantilla(nom);
    }
    public static void afegirDoctorAPlantilla(Plantilla p){
        String dni = arg.next();
        //Si existeix doctor
        if (CtrlDomini.existeixDoctoraHospital(dni)){
            //Si no té cap plantilla
            Doctor doc = CtrlDomini.consultaDoctor(dni);
            if(!doc.isActiu()){
                CtrlDomini.afegirDoctorAPlantilla(doc, p);
            }
            else
                System.out.println("Doctor ja està associat a una plantilla");
        }
        else
            System.out.println("No existeix cap doctor amb dni "+dni);
    }
    public static void treureDoctorDePlantilla(Plantilla p){
        String dni = arg.next();
        if(CtrlDomini.existeixDoctoraHospital(dni)){
            Doctor doc = CtrlDomini.consultaDoctor(dni);
            CtrlDomini.esborrarDoctorDePlantilla(doc, p);
        }
        else
            System.out.println("No existeix cap doctor amb dni "+dni);
    }


    public static void crearIAssociarAmbCalendari(Plantilla p){
        boolean valid = false;
        int any_i = lecturaAny();
        int any_f = lecturaAny();
        CtrlDomini.associaCalendariPlantilla(p, any_i, any_f);
    }
    public static void desassociaIEsborraCalendari(Plantilla p){
        CtrlDomini.desassociaCalendariIEsborra(p);
    }

    public static void casModificaPlantilla(){
        String nom_p = arg.next();
        if(CtrlDomini.existeixPlantilla(nom_p)){
            Plantilla p = CtrlDomini.consultaPlantilla(nom_p);
            escriuMenuModificaPlantilla(p.getNomPlantilla());
            boolean sortir = false;
            while(!sortir){
                int menu = lecturaTeclat();
                switch(menu){
                    case 1: modificaNomPlantilla(p); break;
                    case 2: afegirDoctorAPlantilla(p); break;
                    case 3: treureDoctorDePlantilla(p); break;
                    case 4: crearIAssociarAmbCalendari(p); break;
                    case 5: desassociaIEsborraCalendari(p); break;
                    case 0: sortir = true; break;
                    default:
                        System.out.println("El numero ha d'estar entre 0 i 5.");
                        break;
                }
                if (menu != 0) escriuMenuModificaPlantilla(p.getNomPlantilla());
            }
        }
        else
            System.out.println("No existeix cap plantilla amb nom "+nom_p);
    }
    public static void casConsultaPlantilla(){
        String nom_p = arg.next();
        if(CtrlDomini.existeixPlantilla(nom_p)){
            Plantilla p = CtrlDomini.consultaPlantilla(nom_p);
            TreeSet<Doctor> ll = p.getLlistaDoctorsNom();System.out.println("Doctors de la plantilla "+nom_p+":");
            for (Doctor doc:ll){
                System.out.println("DNI: " + doc.getdni());
                System.out.println("Cognoms, Nom: " + " " + doc.getCognom1() + " " + doc.getCognom2() + ", " + doc.getNom());
                System.out.println("Sou: " + doc.getSou());
                System.out.println("Telefon: " + doc.getTelefon());
                System.out.println("Correu: " + doc.getCorreu() + "\n");
            }
        }
        else {
            System.out.println("No existeix cap plantilla amb el nom "+nom_p+".");
        }
    }
    public static void casEliminaPlantilla(){
        String nom_p = arg.next();
        if(CtrlDomini.existeixPlantilla(nom_p)){
            CtrlDomini.esborraPlantilla(nom_p);
        }else {
            System.out.println("No existeix cap plantilla amb el nom " + nom_p + ".");
        }

    }
    public static void casGuardarPlantilles(){
        CtrlDomini.guardarPlantilles();
    }
    public static void casCarregarPlantilles(){
        CtrlDomini.carregarPlantilles();
    }

    public static void casLlistarPlantilles(){
        TreeSet<Plantilla> Cjt = CtrlDomini.llistarPlantilles();
        System.out.println("Núm. de plantilles: "+Cjt.size());
        for(Plantilla p:Cjt)
            System.out.println(p.getNomPlantilla());
    }

    //-----------------------Principal--------------------
    private static void escriuMenuModificaPlantilla(String nom_p){
        //Permetem canvi del nom de la plantilla
        //Pero no és gaire adequat
        System.out.println("----------Modificar plantilla "+nom_p+"----------");
        System.out.println("1.- Canviar nom(Nom_plantilla: String)");
        System.out.println("2.- Afegir doctor a plantilla (dni: String)"); //Llistem doctors de l'hospital a afegir que no tenen plantilla
        System.out.println("3.- Treure doctor de plantilla (dni: String)");
        System.out.println("4.- Crear i associar amb calendari (any: int)");
        System.out.println("5.- Desassocia i esborra calendari");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");

    }
    private static void escriuMenuGestioPlantilles(){
        System.out.println("----------GESTIÓ DE PLANTILLES----------");
        System.out.println("1.- Crear plantilla(Nom_plantilla: String)");
        System.out.println("2.- Modificar plantilla(Nom_plantilla: String)"); //Crida a menúModificaPlantilla
        System.out.println("3.- Consultar Plantilla(Nom_plantilla: String)");   //Crida menúConsultarPlantilla
        System.out.println("4.- Eliminar Plantilla(Nom_plantilla: String)");
        System.out.println("5.- Guardar plantilles()");
        System.out.println("6.- Carregar plantilles()");
        System.out.println("7.- Consultar llistat plantilles existents()");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioPlantilles(){
        escriuMenuGestioPlantilles();
        boolean sortir = false;
        while (!sortir){
            int menu = lecturaTeclat();
            switch(menu){
                case 1: casCreaPlantilla(); break;
                case 2: casModificaPlantilla(); break;
                case 3: casConsultaPlantilla(); break;
                case 4: casEliminaPlantilla(); break;
                case 5: casGuardarPlantilles(); break;
                case 6: casCarregarPlantilles(); break;
                case 7: casLlistarPlantilles(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 7.");
                    break;
            }
            if(menu != 0) escriuMenuGestioPlantilles();
        }
    }




    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------RESTRICCIONS---------------------
    //----------------------------------------------------
    private static void casNovaRestriccio(){
        String expressio = arg.next();
        CtrlDomini.novaRestriccio(expressio);
    }

    private static void casLlistarRestriccions() {
        System.out.println("El llistat de restriccions és el següent: ");

        ArrayList<Restriccio> llista_r = CtrlDomini.consultaLlistaRestriccions();
        for (int i = 0; i < llista_r.size(); i++) {
            Restriccio restriccio = llista_r.get(i);
            System.out.print(i + 1 + ": ");
            System.out.println(CtrlRestriccio.mostra_arbre(restriccio));
        }
    }
    private static void casEliminaRestriccio(){
        System.out.println("El llistat de restriccions és el següent: ");
        casLlistarRestriccions();
        System.out.println("Quina restricció de totes vols eliminar?");
        int pos = lecturaTeclat();
        Restriccio r = CtrlDomini.consultaRestriccio(pos);
        CtrlDomini.eliminaRestriccio(r);

    }

    private static void casGuardarRestriccions(){
        CtrlDomini.guardarRestriccions();
    }
    private static void casCarregarRestriccions(){
        CtrlDomini.carregarRestriccions();
    }

    private static void escriuMenuGestioRestriccions(){
        System.out.println("----------GESTIÓ DE RESTRICCIONS----------");
        System.out.println("1.- Nova restriccio (expressio: String)");
        System.out.println("2.- Elimina restriccio");
        System.out.println("3.- Guardar restriccions");
        System.out.println("4.- Carregar restriccions");
        System.out.println("5.- Mostra llista restriccions");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioRestriccions(){
        escriuMenuGestioRestriccions();
        boolean sortir = false;
        while (!sortir){
            int menu = lecturaTeclat();
            switch(menu){
                case 1: casNovaRestriccio(); break;
                case 2: casEliminaRestriccio(); break;
                case 3: casGuardarRestriccions(); break;
                case 4: casCarregarRestriccions(); break;
                case 5: casLlistarRestriccions(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 5.");
                    break;
            }
        }

    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------ALGORISMES---------------------
    //----------------------------------------------------

    private static void casAplicarFF() throws Error {
        Graf graf = CtrlDomini.crear_graf();
        CtrlDomini.aplicaFordFulkerson(graf);
        graf.mostra_graf();
    }
    private static void casAplicarEK() throws Error {
        Graf graf = CtrlDomini.crear_graf();
        CtrlDomini.aplicaEdmondsKarp(graf);
        graf.mostra_graf();
    }
    private static void casComparaResultatsAlg() throws Error{
        Graf GFF = CtrlDomini.crear_graf();
        Graf GEK = CtrlDomini.crear_graf();

        CtrlDomini.aplicaFordFulkerson(GFF);
        CtrlDomini.aplicaEdmondsKarp(GEK);

        System.out.println("Graf de Ford Fulkerson: ");
        GFF.mostra_graf();
        System.out.println("Graf d'Edmond's Karp: ");
        GEK.mostra_graf();
    }

    private static void escriuMenuAplicarAlgorisme(){
        System.out.println("----------APLICAR ALGORISME----------");
        System.out.println("1.- Aplica algorisme de Ford Fulkerson");
        System.out.println("2.- Aplica algorisme de Edmond's Karp");
        System.out.println("3.- Compara resultats d'algorismes");   //Si no s'han aplicat, mostrar missatge d'error
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casAplicarAlgorisme() throws Error {
        escriuMenuAplicarAlgorisme();
        boolean sortir = false;
        while(!sortir) {
            int menu = lecturaTeclat();
            switch(menu){
                case 1: casAplicarFF(); break;
                case 2: casAplicarEK(); break;
                case 3: casComparaResultatsAlg(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 3.");
                    break;
            }
        }
    }

    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------GUARDAR---------------------
    //----------------------------------------------------

    private static void casGuardar(){
       CtrlDomini.guardarGeneral();
    }
    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------CARREGAR---------------------
    //----------------------------------------------------

    private static void casCarregar(){
        CtrlDomini.carregarGeneral();
    }



    //-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    //----------------------------------------------------
    //-----------------------CALENDARIS---------------------
    //----------------------------------------------------
    
    public static void casCreaCalendari(){
        String nom_p = arg.next();
        boolean valid = false;
        int any_i = 0;
        while(!valid){
            try {
                any_i = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Any d'inici ha de ser un enter.");
                System.out.print("Torna a introduir any d'inici: ");
                arg.next();
                continue;
            }
        }
        int any_f = 0;
        valid = false;
        while(!valid){
            try {
                any_f = arg.nextInt();
                valid = true;
            } catch (Exception e){
                System.out.println("Any final ha de ser un enter.");
                System.out.print("Torna a introduir any final: ");
                arg.next();
                continue;
            }
        }

        if(!CtrlDomini.existeixCalendari(nom_p)){
            CtrlDomini.crearCalendari(nom_p,any_i,any_f);
        }
        else
            System.out.println("Ja existeix calendari per a la plantilla " + nom_p);

    }
    public static boolean esAnyDeTraspas(int any){
        //Retorna true si l'any és múltiple de 4
        //i no és multiple de cent pero si que es multiple de 400

        return (any % 4 == 0) && !(any % 100 == 0 && any % 400 != 0);
    }
    public static int lecturaAny(){
        boolean valid = false;
        int any = 0;
        while(!valid){
            try{
                any = arg.nextInt();
                if (any > 0) valid = true;
                else {
                    System.out.println("Any ha de tenir un valor positiu.");
                    System.out.print("Torna a introduir any: ");
                }
            } catch (Exception e){
                System.out.println("Any ha de ser un enter.");
                System.out.print("Torna a introduir any: ");
                arg.next();
                continue;
            }
        }
        return any;
    }
    public static int lecturaMes(){
        boolean valid = false;
        int mes = 0;
        while(!valid){
            try{
                mes = arg.nextInt();
                if(mes > 0 && mes < 13) valid = true;
                else{
                    System.out.println("Mes ha de tenir un valor entre 1 i 12.");
                    System.out.print("Torna a introduir mes: ");
                }
            } catch(Exception e){
                System.out.println("Mes ha de ser un enter.");
                System.out.print("Torna a introduir mes: ");
                arg.next();
                continue;
            }
        }
        return mes;
    }
    public static int lecturaDia(int any, int mes){
        boolean valid = false;
        int dia = 0;
        while(!valid){
            try{
                dia = arg.nextInt();
                //Mesos de 31 dies
                if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                    if (dia > 0 && dia < 32) valid = true;
                    else{
                        System.out.println("El mes "+mes+" té un rang de dies de l'1 fins al 31");
                        System.out.print("Torna a introduir dia: ");
                    }
                }
                //Mes de febrer
                else if (mes == 2){
                    if(esAnyDeTraspas(any)){
                        // 1 =< Dia =< 29
                        if(dia > 0 && dia < 30) valid = true;
                        else {
                            System.out.println("El mes "+mes+" en l'any "+any+" té un rang de dies de l'1 fins al 29");
                            System.out.print("Torna a introduir dia: ");
                        }
                    }
                    //No és any de traspàs
                    else{
                        //28 dies
                        if(dia > 0 && dia < 29) valid = true;
                        else {
                            System.out.println("El mes "+mes+" en l'any "+any+" té un rang de dies de l'1 fins al 28");
                            System.out.print("Torna a introduir dia: ");
                        }
                    }
                }
                //Mesos de 30 dies
                else{
                    if (dia > 0 && dia < 31) valid = true;
                    else{
                        System.out.println("El mes "+mes+" té un rang de dies de l'1 fins al 30");
                        System.out.print("Torna a introduir dia: ");
                    }
                }
            } catch (Exception e){
                System.out.println("Dia ha de ser un enter.");
                System.out.print("Torna a introduir dia: ");
                arg.next();
                continue;
            }
        }
        return dia;
    }
    public static boolean lecturaFestiu(){
        boolean valid = false;
        boolean b = false;
        while(!valid){
            try{
                b = arg.nextBoolean();
                valid = true;
            } catch (Exception e){
                System.out.println("Festiu ha de ser un boolea.");
                System.out.print("Torna a introduir festiu (true o false)");
                arg.next();
                continue;
            }
        }
        return b;
    }
    public static int lecturaHorari(){
        boolean valid = false;
        int h = 0;
        while(!valid){
            try{
                h = arg.nextInt();
                if(h < 3 && h >= 0) valid = true;
                else{
                    System.out.println("Horari ha de tenir un valor entre 0 i 2.");
                    System.out.println("Matí = 0  //  Tarda = 1  //  Nit = 2");
                    System.out.println("Torna a introduir horari: ");
                }
            } catch (Exception e){
                System.out.println("Horari ha de ser un enter.");
                System.out.print("Torna a introduir horari: ");
                arg.next();
                continue;

            }
        }
        return h;
    }
    public static float lecturaPercentatge(){
        boolean valid = false;
        float per = 0;
        while(!valid){
            try{
                per = arg.nextFloat();
                if(per >= 0) valid = true;
                else{
                    System.out.println("Percentatge ha de ser positiu.");
                    System.out.print("Torna a introduir el percentatge: ");
                }
            } catch (Exception e){
                System.out.println("Percentatge ha de ser un núm.");
                System.out.print("Torna a introduir el percentatge: ");
                arg.next();
                continue;
            }
        }
        return per;
    }
    public static int lecturaNumMinimDocs(){
        boolean valid = false;
        int num_min = 0;
        while(!valid){
            try{
                num_min = arg.nextInt();
                if (num_min >= 0) valid = true;
                else{
                    System.out.println("Número mínim de doctors ha de ser un nombre positiu.");
                    System.out.print("Torna a introduir el numero minim de docs: ");
                }
            } catch (Exception e){
                System.out.println("Numero mínim de docs ha de ser un enter.");
                System.out.print("Torna a introduir el numero minim de docs: ");
                arg.next();
                continue;
            }
        }
        return num_min;
    }



    public static void modificarDiaFestiu(String nom) {
        int any = lecturaAny();
        int mes = lecturaMes();
        int dia = lecturaDia(any, mes);
    	GregorianCalendar d = new GregorianCalendar(any,mes,dia);
        boolean b = lecturaFestiu();
    	CtrlDomini.modificarDiaFestiu(nom,d,b);
    }
    public static void modificarPercentatge(String nom) {
    	int any = lecturaAny();
        int mes = lecturaMes();
        int dia = lecturaDia(any, mes);
    	GregorianCalendar d = new GregorianCalendar(any,mes,dia);
    	int horari = lecturaHorari();
    	float p = lecturaPercentatge();
    	CtrlDomini.modificarPercentatge(nom,d,horari,p);
    }
    public static void modificarMinim(String nom) {
    	int any = lecturaAny();
        int mes = lecturaMes();
    	int dia = lecturaDia(any, mes);
    	GregorianCalendar d = new GregorianCalendar(any,mes,dia);
    	int horari = lecturaHorari();
    	int p = lecturaNumMinimDocs();
    	CtrlDomini.modificarMinim(nom,d,horari,p);
    }
    public static void borrarDia(String nom) {
        int any = lecturaAny();
        int mes = lecturaMes();
        int dia = lecturaDia(any, mes);
    	GregorianCalendar d = new GregorianCalendar(any,mes,dia);
    	CtrlDomini.borrarDia(nom,d);
    }
    public static void borrarTorn(String nom) {
        int any = lecturaAny();
        int mes = lecturaMes();
        int dia = lecturaDia(any, mes);
    	GregorianCalendar d = new GregorianCalendar(any,mes,dia);
    	int h = lecturaHorari();
    	CtrlDomini.borrarTorn(nom,d,h);
    }
    public static void casModificarCalendari() {
        String nom_p = arg.next();
        if(CtrlDomini.existeixCalendari(nom_p)){
            escriuMenuModificaCalendari(nom_p);
            boolean sortir = false;
            while(!sortir){
                int menu = lecturaTeclat();
                switch(menu){
                    case 1: modificarDiaFestiu(nom_p); break;
                    case 2: modificarPercentatge(nom_p); break;
                    case 3: modificarMinim(nom_p); break;
                    case 4: borrarDia(nom_p); break;
                    case 5: borrarTorn(nom_p); break;
                    case 0: sortir = true; break;
                    default:
                        System.out.println("El numero ha d'estar entre 0 i 5.");
                        break;
                }
                if (menu != 0) escriuMenuModificaCalendari(nom_p);
            }
        }
        else
            System.out.println("No existeix cap plantilla amb nom "+nom_p);
    
    }
    public static void escriureDia(Dia d, GregorianCalendar data) {
		String da = data.getTime().toString();
		System.out.println("El dia "+da);
		if(d.getFestiu())System.out.println("Es festiu");
		else System.out.println("No es festiu");
		Torn t;
		String s;
		for(int i=0; i<3; ++i) {
			if(i==0) {
				s="Mati";
				t = d.getTornMati();
			}
			else if(i==1) {
				s="Tarda";
				t = d.getTornTarda();
			}
			else {
				s="Nit";
				t = d.getTornNit();
			}
			System.out.println("Torn de "+s+":\n"
					+ "Hora inici: "+t.getHora_inici()+"\n"
					+ "Hora fi: "+t.getHora_fi()+"\n"
					+ "Percentatge de sou: "+t.getPercent_sou()+"\n"
					+ "Numero minim de doctors: "+t.getMin_num_doctors()+"\n\n");
		}
	}
    public static void casConsultarCalendari() {
		String plt = arg.next();
		Calendari c = CtrlDomini.consultaCalendari(plt);
		for(int i=0; i<c.getCalendari().length; ++i){
			Dia d = c.getCalendari()[i];
			escriureDia(d,CtrlCalendari.quinDia(i,c.getAny()));
		}
    }
    public static void casEliminaCalendari(){
        String nom_p = arg.next();
        if(CtrlDomini.existeixCalendari(nom_p)){
            CtrlDomini.esborrarCalendari(nom_p);
        }else {
            System.out.println("No existeix cap calendari per la plantilla " + nom_p + ".");
        }
    }
    public static void casGuardarCalendaris(){
        CtrlDomini.guardarCalendaris();
    }
    public static void casCarregarCalendaris(){
        CtrlDomini.carregarCalendaris();
    }
    public static void casLlistarCalendaris(){
        TreeSet<Calendari> Cjt = CtrlDomini.llistarCalendaris();
        System.out.println("Núm. de plantilles: "+Cjt.size());
        for(Calendari c:Cjt)
            System.out.println(c.getPlantillaAssociada());
    }
    private static void escriuMenuModificaCalendari(String nom_p){
        //Permetem canvi del nom de la plantilla
        //Pero no és gaire adequat
        System.out.println("----------Modificar calendari associat a la plantilla: "+nom_p+"----------");
        System.out.println("1.- Canviar plantilla associada al calendari(Nom_plantilla: String)");
        System.out.println("2.- Modificar Dia Festiu(Data(any: int, mes: int, dia: int), Festiu?: boolea)"); //Llistem doctors de l'hospital a afegir que no tenen plantilla
        System.out.println("3.- Modificar percentatge de sou d'un Torn(Data (any: int, mes: int, dia: int), Torn: (mati=0, tarda=1 o nit=2), nou percentatge: Float)");
        System.out.println("4.- Modificar número mínim de doctors d'un Torn(Data (any: int, mes: int, dia: int), Torn: (mati=0, tarda=1 o nit=2), nou minim: int)");
        System.out.println("5.- Borrar informació d'un dia(Data (any: int, mes: int, dia: int))");
        System.out.println("6.- Borrar informació d'un torn(Data (any: int, mes: int, dia: int), Torn(mati=0, tarda=1, nit=2)");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");

    }
    private static void escriuMenuGestioCalendaris(){
        System.out.println("----------GESTIÓ DE CALENDARIS----------");
        System.out.println("1.- Crear Calendari(Nom_plantilla: String, any_inici: int, any_fi: int)");
        System.out.println("2.- Modificar Calendari(Nom_plantilla: String)"); //Crida a menúModificaCalendari
        System.out.println("3.- Consultar Calendari(Nom_plantilla: String)");  
        System.out.println("4.- Eliminar Calendari(Nom_plantilla: String)");
        System.out.println("5.- Guardar calendaris()");
        System.out.println("6.- Carregar calendari()");
        System.out.println("7.- Consultar llistat de plantilles associades a calendaris()");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioCalendaris(){
        escriuMenuGestioCalendaris();
        boolean sortir = false;
        while (!sortir){
            int menu = lecturaTeclat();
            switch(menu){
                case 1: casCreaCalendari(); break;
                case 2: casModificarCalendari(); break;
                case 3: casConsultarCalendari(); break;
                case 4: casEliminaCalendari(); break;
                case 5: casGuardarCalendaris(); break;
                case 6: casCarregarCalendaris(); break;
                case 7: casLlistarCalendaris(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 7.");
                    break;
            }
            if(menu != 0) escriuMenuGestioPlantilles();
        }

    }

}
*/