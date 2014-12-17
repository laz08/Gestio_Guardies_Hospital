package prop;


import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

public class DriverCtrlPlantilla {

    static Scanner arg = new Scanner(System.in);
    public static void main(String[] args) {

        int cas = 0;
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
                    case 1: casCreaPlantilla(); break;
                    case 2: casEliminaPlantilla(); break;
                    case 3: casExisteixPlantilla(); break;
                    case 4: casConsultarDocsPlantilla(); break;
                    case 5: casConsultarnumPlantilles(); break;
                    case 6: casConsultarLlistatPlantilles(); break;

                    case 7: casAfegirDocAPlantilla(); break;
                    case 8: casEsborrarDocdePlantilla(); break;
                    case 9: casDoctorTePlantilla(); break;
                    case 10: casDoctorEstaEnPlantilla(); break;

                    case 11: casCreaDoctor(); break;
                    case 12: casEliminaDoctor(); break;
                    case 13: casExisteixDoctor(); break;
                    case 14: casConsultaDoctor(); break;
                    case 15: casLlistatDoctors(); break;

                    case 16: casEscollirPlantillaActual(); break;
                    case 17: casConsultarPlantillaActual(); break;
                    case 18: casGuardarPlantilles(); break;
                    case 19: casCarregarPlantilles(); break;

                    case 0: sortir = true; break;
                    default:
                        System.out.println("El numero ha d'estar entre 0 i 19.");
                        break;
                }

            if(cas != 0) escriuMenu();

        }
    }

    public static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.println("\nPlantilles en general");
        System.out.println("1.- Crear i afegir plantilla (Nom_plantilla: String)");
        System.out.println("2.- Eliminar Plantilla(Nom_plantilla: String)");
        System.out.println("3.- Existeix Plantilla(Nom_plantilla: String)");
        System.out.println("4.- Consultar Plantilla(Nom_plantilla: String)");
        System.out.println("5.- Consultar num de plantilles()");
        System.out.println("6.- Consultar llistat plantilles existents()");
        System.out.println("18.- Guardar plantilles()");
        System.out.println("19.- Carregar plantilles()");
        System.out.println("\nPlantilles i els seus doctors");
        System.out.println("7.- Afegir doctor a plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("8.- Esborrar doctor de plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("9.- Doctor te plantilla (dni: String)");
        System.out.println("10.- Doctor esta en plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("\nDoctors (en Hospital)");
        System.out.println("11.- Crear Doctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("12.- Eliminar Doctor(dni: String)");
        System.out.println("13.- Existeix Doctor(dni: String)");
        System.out.println("14.- Consultar Doctor(dni: String)");
        System.out.println("15.- Consultar llistat doctors()");
        System.out.println("\nPlantilla actual");
        System.out.println("16.- Escollir Plantilla actual()");
        System.out.println("17.- Consultar Plantilla actual()");
        System.out.println("0.- Exit");
        System.out.println("---------------------------------");
        System.out.print(">> ");

    }

    public static void missatgeErrorDni(){
        System.out.println("Ja existeix un doctor amb aquest DNI.");
        System.out.println("El llistat dels dni de doctors ja existents es el seguent: ");
        TreeSet<Doctor> ll = CtrlHospital.getHospital_dni();
        for (Doctor doc:ll)
            System.out.println(doc.getdni());

        System.out.println("Introdueix el DNI de nou d'aquest metge.");
        System.out.println("(Les altres dades es mantindran)");
        System.out.print(">>");

    }

    public static void casCreaPlantilla(){
        String nom_p = arg.next();
        // Si no existeix una plantilla amb aquest nom...
        if(!CtrlPlantilla.existeixPlantilla(nom_p))
            CtrlPlantilla.creariAfegirPlantilla(nom_p);
        else
            System.out.println("Ja existeix plantilla amb nom " + nom_p);

    }

    public static void casEliminaPlantilla(){
        String nom_p = arg.next();
        //Si existeix una plantilla amb aquest nom
        if(CtrlPlantilla.existeixPlantilla(nom_p))
            CtrlPlantilla.esborrarPlantilla(nom_p);
        else
            System.out.println("No existeix cap plantilla amb el nom " + nom_p);
    }


    public static void casExisteixPlantilla(){
        String nom_p = arg.next();
        if(CtrlPlantilla.existeixPlantilla(nom_p)){
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

    public static void casConsultarDocsPlantilla(){
        String nom_p = arg.next();
        if(CtrlPlantilla.existeixPlantilla(nom_p)){
            Plantilla p = CtrlPlantilla.consultarPlantilla(nom_p);
            TreeSet<Doctor> ll = p.getLlistaDoctorsNom();
            System.out.println("Doctors de la plantilla "+nom_p+":");
            for (Doctor doc:ll){
                System.out.println("dni: " + doc.getdni());
                System.out.println("nom i cognoms: " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2());
                System.out.println("sou: " + doc.getSou());
                System.out.println("Telefon: " + doc.getTelefon());
                System.out.println("Correu: " + doc.getCorreu() + "\n");
            }

        }
        else {
            System.out.println("No existeix cap plantilla amb el nom "+nom_p+".");
        }
    }

    public static void casConsultarnumPlantilles(){
        System.out.println(CtrlPlantilla.num_plantilles());
    }

    public static void casAfegirDocAPlantilla(){
        String dni = arg.next();
        String nom_p = arg.next();

        if(CtrlHospital.existeixDoctor(dni)){
            if(!CtrlPlantilla.docTePlantilla(dni)){
                if(CtrlPlantilla.existeixPlantilla(nom_p)){
                    CtrlPlantilla.afegirDoctorAPlantilla(dni, nom_p);
                }
                else
                    System.out.println("No existeix cap plantilla amb el nom "+nom_p+".");
            }
            else System.out.println("El doctor ja està assignat a una plantilla.");
        }
        else System.out.println("No existeix el doctor amb dni "+dni+" a l'hospital.");
    }

    public static void casEsborrarDocdePlantilla() {
        String dni = arg.next();
        String nom_p = arg.next();

        if(CtrlHospital.existeixDoctor(dni)){
            if(CtrlPlantilla.docTePlantilla(dni)){
                if(CtrlPlantilla.existeixPlantilla(nom_p)){
                    CtrlPlantilla.esborrarDoctordePlantilla(dni, nom_p);
                }
                else
                    System.out.println("No existeix cap plantilla amb el nom "+nom_p+".");
            }
            else
                System.out.println("El doctor no està assignat a cap plantilla.");
        }
         else
            System.out.println("No existeix el doctor amb dni "+dni);
    }

    public static void casDoctorTePlantilla(){
        String dni = arg.next();
        if (CtrlHospital.existeixDoctor(dni)) {
            if (!CtrlPlantilla.docTePlantilla(dni)) {
                System.out.println("El doctor no té cap plantilla.");
            } else {
                System.out.println("El doctor ja està assignat a una plantilla.");
            }
        }
        else {
            System.out.println("No existeix cap doctor amb dni "+dni+".");
        }
    }

    public static void casDoctorEstaEnPlantilla(){
        String dni = arg.next();
        String nom_p = arg.next();

        if(CtrlHospital.existeixDoctor(dni)){
            if(CtrlPlantilla.docTePlantilla(dni)){
                if (CtrlPlantilla.existeixPlantilla(nom_p)) {
                    if (CtrlPlantilla.docEnPlantilla(dni, nom_p)) {
                        System.out.println("true");
                    }
                    else System.out.println("false");
                }
                else
                    System.out.println("No existeix cap plantilla amb el nom "+nom_p);
            }
            else {
                System.out.println("El doctor no està assignat a cap plantilla");
            }
        }
        else{
            System.out.println("No existeix el doctor amb dni "+dni+" a l'hospital");
        }
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


        /*
        Comprovem si existeix doctor amb aquest DNI
         */
        if (CtrlHospital.existeixDoctor(dni)){
            valid = false;
        }

        while(!valid){
            missatgeErrorDni();
            dni = arg.next();
            if(!CtrlHospital.existeixDoctor(dni)) valid = true;
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
            System.out.println("\n");
            System.out.println("DNI: " + doc.getdni());
            System.out.println("nom i cognoms: " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2());
            System.out.println("sou: " + doc.getSou());
            System.out.println("Telefon: " + doc.getTelefon());
            System.out.println("Correu: " + doc.getCorreu());
        }
    }

    public static void casConsultarLlistatPlantilles(){
        TreeSet<Plantilla> Cjt = CtrlPlantilla.getCjt_plantilles();
        for(Plantilla p:Cjt)
            System.out.println(p.getNomPlantilla());
    }

    public static void casEscollirPlantillaActual(){
        String nom_p = arg.next();
        if(CtrlPlantilla.existeixPlantilla(nom_p)){
            CtrlPlantilla.setPlantillaActual(nom_p);
        }
        else
            System.out.println("No existeix cap plantilla amb nom "+nom_p+".");
    }

    public static void casConsultarPlantillaActual(){
        if (CtrlPlantilla.getPlantillaActual() != null)
            System.out.println(CtrlPlantilla.getPlantillaActual().getNomPlantilla());
        else System.out.println("Ara mateix no tenim cap plantilla seleccionada.");
    }

    public static void casGuardarPlantilles() {
    	File f = new File("");
        CtrlPlantilla.guardar(f);
    }

    public static void casCarregarPlantilles() {
        File f = new File("");
        CtrlPlantilla.carregar(f);
    }

}