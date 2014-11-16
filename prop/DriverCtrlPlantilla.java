package prop;

import java.util.Scanner;

public class DriverCtrlPlantilla {


    public static void main(String[] args) {

        Scanner arg;
        int cas;
        boolean sortir = false;
        while (!sortir) {
            try {
                escriuMenu();

                arg = new Scanner(System.in);
                cas = arg.nextInt();

                switch (cas) {
                    case 1: casCreaPlantilla(); break;
                    case 2: casEliminaPlantilla(); break;
                    case 3: casExisteixPlantilla(); break;
                    case 4: casConsultarPlantilla(); break;
                    case 5: casConsultarnumPlantilles(); break;

                    case 6: casAfegirDocAPlantilla(); break;
                    case 7: casEsborrarDocdePlantilla(); break;
                    case 8: casDoctorTePlantilla(); break;
                    case 9: casDoctorEstaEnPlantilla(); break;

                    case 10: casCreaDoctor(); break;
                    case 11: casEliminaDoctor(); break;
                    case 12: casExisteixDoctor(); break;
                    case 13: casConsultaDoctor(); break;
                    case 14: casLlistatDoctors(); break;
                    case 0: sortir = true; break;
                    default:
                        System.out.println("El numero ha d'estar entre 0 i 14.");
                        break;
                }

            }catch (Exception e){
                System.err.println("Has d'introduir un numero\n"+e);
            }
        }
    }

    public static void escriuMenu(){
        System.out.println("\n\n---------Menú---------");
        System.out.println("\n*-*-Plantilles en general-*-*");
        System.out.println("1.- Crear i afegir plantilla (Nom_plantilla: String)");
        System.out.println("2.- Eliminar Plantilla(Nom_plantilla: String)");
        System.out.println("3.- Existeix Plantilla(Nom_plantilla: String)");
        System.out.println("4.- Consultar Plantilla(Nom_plantilla: String)");
        System.out.println("5.- Consultar num de plantilles()");
        System.out.println("\n*-*-Plantilles i els seus doctors-*-*");
        System.out.println("6.- Afegir doctor a plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("7.- Esborrar doctor de plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("8.- Doctor te plantilla (dni: String)");
        System.out.println("9.- Doctor esta en plantilla (dni: String, nom_Plantilla: string)");
        System.out.println("\n*-*-Doctors (en Hospital) -*-*");
        System.out.println("10.- Crear Doctor(dni: String, nom: String, cognom1: String, cognom2: String, sou: int, telf: int, correu: String)");
        System.out.println("11.- Eliminar Doctor(dni: String)");
        System.out.println("12.- Existeix Doctor(dni: String)");
        System.out.println("13.- Consultar Doctor(dni: String)");
        System.out.println("14.- Consultar llistat doctors()");
        System.out.println("0.- Exit");

    }

    public static void casCreaPlantilla(){
        Scanner sc = new Scanner(System.in);
        String nom_p = sc.next();
        // Si no existeix una plantilla amb aquest nom...
        if(CtrlPlantilla.existeixPlantilla(nom_p) == -1){
            CtrlPlantilla.creariAfegirPlantilla(nom_p);
        }
        else{
            System.out.println("Ja existeix plantilla amb nom "+nom_p);
        }
    }

    public static void casEliminaPlantilla(){
        Scanner sc = new Scanner(System.in);
        String nom_p = sc.next();
        int pos = CtrlPlantilla.existeixPlantilla(nom_p);
        if(pos != -1){
            CtrlPlantilla.esborrarPlantilla(pos);
        }
        else {
            System.out.println("No existeix cap plantilla amb el nom "+nom_p);
        }
    }

    public static void casExisteixPlantilla(){
        Scanner sc = new Scanner(System.in);
        String nom_p = sc.next();
        int pos = CtrlPlantilla.existeixPlantilla(nom_p);
        if(pos != -1){
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }

    public static void casConsultarPlantilla(){
        Scanner sc = new Scanner(System.in);
        String nom_p = sc.next();
        int pos = CtrlPlantilla.existeixPlantilla(nom_p);
        if(pos != -1){
           Plantilla p = CtrlPlantilla.consultarPlantilla(pos);
            System.out.println("Doctors de la plantilla "+nom_p);
            Doctor doc = null;
            for (int i = 0; i < p.getLlistaDoctors().size(); ++i) {
                doc = p.getLlistaDoctors().get(i);
                System.out.println("dni: " + doc.getdni());
                System.out.println("nom i cognoms: " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2());
                System.out.println("sou: " + doc.getSou());
                System.out.println("Telefon: " + doc.getTelefon());
                System.out.println("Correu: " + doc.getCorreu() + "\n");
            }

        }
        else {
            System.out.println("No existeix cap plantilla amb el nom "+nom_p);
        }
    }

    public static void casConsultarnumPlantilles(){
        System.out.println(CtrlPlantilla.num_plantilles());
    }

    public static void casAfegirDocAPlantilla() throws Error {
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        String nom_p = sc.next();

        if(CtrlHospital.existeixDoctor(dni) != -1){
            if(!CtrlPlantilla.docTePlantilla(dni)){
                if (CtrlPlantilla.existeixPlantilla(nom_p) != -1)
                    CtrlPlantilla.afegirDoctorAPlantilla(dni, nom_p);
                else
                    System.out.println("No existeix cap plantilla amb el nom "+nom_p);
            }
            else {
                System.out.println("El doctor ja està assignat a una plantilla");
            }
        }
        else{
            System.out.println("No existeix el doctor amb dni "+dni+" a l'hospital");
        }
    }

    public static void casEsborrarDocdePlantilla() throws Error {
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        String nom_p = sc.next();

        if(CtrlHospital.existeixDoctor(dni) != -1) {
            if (!CtrlPlantilla.docTePlantilla(dni)) {
                if (CtrlPlantilla.existeixPlantilla(nom_p) != -1)
                    CtrlPlantilla.esborrarDoctordePlantilla(dni, nom_p);
                else
                    System.out.println("No existeix cap plantilla amb el nom " + nom_p);
            } else {
                System.out.println("El doctor ja està assignat a una plantilla");
            }
        }
        else
            System.out.println("No existeix el doctor amb dni "+dni);
    }

    public static void casDoctorTePlantilla(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        if (CtrlHospital.existeixDoctor(dni) != -1) {
            if (!CtrlPlantilla.docTePlantilla(dni)) {
                System.out.println("El doctor no té cap plantilla");
            } else {
                System.out.println("El doctor ja està assignat a una plantilla");
            }
        }
        else {
            System.out.println("No existeix cap doctor amb dni "+dni);
        }
    }

    public static void casDoctorEstaEnPlantilla(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        String nom_p = sc.next();

        if(CtrlHospital.existeixDoctor(dni) != -1){
            if(!CtrlPlantilla.docTePlantilla(dni)){
                if (CtrlPlantilla.existeixPlantilla(nom_p) != -1)
                    CtrlPlantilla.docEnPlantilla(dni, nom_p);
                else
                    System.out.println("No existeix cap plantilla amb el nom "+nom_p);
            }
            else {
                System.out.println("El doctor ja està assignat a una plantilla");
            }
        }
        else{
            System.out.println("No existeix el doctor amb dni "+dni+" a l'hospital");
        }
    }



    public static void casCreaDoctor(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        String nom = sc.next();
        String cg1 = sc.next();
        String cg2 = sc.next();
        int sou = 0;
        int telf = 0;
        sou = sc.nextInt();
        telf = sc.nextInt();
        String correu = sc.next();
        if(CtrlHospital.existeixDoctor(dni) == -1) CtrlHospital.creariAfegirDoctor(dni, nom, cg1, cg2, sou, telf, correu);
        else System.out.println("Ja existeix un doctor amb dni == "+dni);
    }

    public static void casEliminaDoctor(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();

        int pos = CtrlHospital.existeixDoctor(dni);
        if (pos != -1){
            CtrlHospital.eliminarDoctor(pos);
        }
    }

    public static void casExisteixDoctor(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        int pos = CtrlHospital.existeixDoctor(dni);
        if (pos != -1)
            System.out.println("true");
        else
            System.out.println("false");
    }

    public static void casConsultaDoctor(){
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        int pos = CtrlHospital.existeixDoctor(dni);
        if (pos != -1){
            Doctor doc = CtrlHospital.getDoctor(pos);
            System.out.println("dni: "+doc.getdni());
            System.out.println("nom i cognoms: "+doc.getNom()+" "+doc.getCognom1()+" "+doc.getCognom2());
            System.out.println("sou: "+doc.getSou());
            System.out.println("Telefon: "+doc.getTelefon());
            System.out.println("Correu: "+doc.getCorreu());
        }
    }

    public static void casLlistatDoctors(){
        Doctor doc;
        for (int i = 0; i < CtrlHospital.numDocs(); ++i){
            doc = CtrlHospital.getDoctor(i);
            System.out.println("\n\n");
            System.out.println("Doctor num.: "+ i);
            System.out.println("dni: "+doc.getdni());
            System.out.println("nom i cognoms: "+doc.getNom()+" "+doc.getCognom1()+" "+doc.getCognom2());
            System.out.println("sou: "+doc.getSou());
            System.out.println("Telefon: "+doc.getTelefon());
            System.out.println("Correu: "+doc.getCorreu());
        }
    }


}