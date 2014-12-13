package prop;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Main per consola
 * (2a entrega)
 */
public class main {

    static Scanner arg = new Scanner(System.in);

    public static void main(String[] args){
        int cas = 0;
        boolean sortir = false;
        escriuMenu();

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
                case 3: casGestioRestriccions(); break;
                case 4: casAplicarAlgorisme(); break;
                case 5: casGuardar(); break;
                case 6: casCarregar(); break;
                case 0: sortir = true; break;
                default:
                    System.out.println("El numero ha d'estar entre 0 i 6.");
                    break;
            }

            if (cas != 0) escriuMenu();
        }
    }

    /**
     * A editar
     */
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

    private static void lecturaTeclatGestioDoctors(){
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
        if (CtrlDomini.existeixDoctoraHospital(dni)){
            valid = false;
        }

        while(!valid){
            missatgeErrorDni();
            dni = arg.next();
            if(!CtrlDomini.existeixDoctoraHospital(dni)) valid = true;
        }

        CtrlDomini.creaDoctor(dni, nom, cg1, cg2, sou, telf, correu);

    }

    private static void lecturaTeclatGestioPlantilles(){

    }

    private static void lecturaTeclatGestioRestriccions(){

    }

    private static void lecturaTeclatAplicarAlgorisme(){

    }

    private static void lecturaTeclatGuardar(){

    }

    private static void lecturaTeclatCarregar(){

    }


    private static void escriuMenu(){
        System.out.println("----------MENU PRINCIPAL----------");
        System.out.println("1.- Gestió de doctors (Hospital)");
        System.out.println("2.- Gestió de plantilles");
        System.out.println("3.- Gestió de Restriccions");
        System.out.println("4.- Escollir algorisme a aplicar");
        System.out.println("5.- Guardar");
        System.out.println("6.- Carregar");
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
        lecturaTeclatGestioDoctors();
        //Crida a CtrlDomini pel cas d'us escollit
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
        lecturaTeclatGestioPlantilles();
        //Crida a CtrlDomini pel cas d'ús escollit (O a un altre menú)
    }

    private static void escriuMenuGestioRestriccions(){
        System.out.println("----------GESTIÓ DE RESTRICCIONS----------");
        System.out.println("1.- Nova restriccio");
        System.out.println("2.- Consulta restriccio");
        System.out.println("3.- Elimina restriccio");
        System.out.println("4.- Guardar restriccions");
        System.out.println("5.- Carregar restriccions");
        System.out.println("6.- Mostra llista restriccions");
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casGestioRestriccions(){
        escriuMenuGestioRestriccions();
        lecturaTeclatGestioRestriccions();
        //Crida a CtrlDomini si s'escau, o a un altre menú

    }

    private static void escriuMenuAplicarAlgorisme(){
        System.out.println("----------APLICAR ALGORISME----------");
        System.out.println("1.- Escull algorisme a aplicar");   //Crida a menuEscullAlgorisme (FF o EK)
        System.out.println("2.- Compara resultats d'algorismes");   //Si no s'han aplicat, mostrar missatge d'error
        System.out.println("0.- Tornar a Menu Principal");
        System.out.println("---------------------------------");
        System.out.print(">> ");
    }
    private static void casAplicarAlgorisme(){
        escriuMenuAplicarAlgorisme();
        lecturaTeclatAplicarAlgorisme();
    }

    private static void escriuMenuGuardar(){

    }
    private static void casGuardar(){
        escriuMenuGuardar();
        lecturaTeclatGuardar();
    }

    private static void escriuMenuCarregar(){
    }
    private static void casCarregar(){
        escriuMenuCarregar();
        lecturaTeclatCarregar();
    }
}
