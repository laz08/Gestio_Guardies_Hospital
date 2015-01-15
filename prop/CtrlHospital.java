
package prop;

import java.io.File;
import java.util.*;

public class CtrlHospital {


    /**
     * Crea un nou doctor amb els atributs especificats a contunuació i en retorna l'objecte
     * @param d DNI del doctor
     * @param n Nom
     * @param cg1 Primer Cognom 
     * @param cg2 Segon Cognom
     * @param s sou
     * @param telf número de telefon
     * @param mail correu electrònic
     * @return Objecte Doctor que conté els atributs especificats
     */
    public static Doctor crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu
        return doc;
    }



    /**
     * Crea un nou doctor amb els atributs especificats a contunuació i l'afegeix a l'hospital
     * @param d DNI del doctor
     * @param n Nom
     * @param cg1 Primer Cognom 
     * @param cg2 Segon Cognom
     * @param s sou
     * @param telf número de telefon
     * @param mail correu electrònic
     */
    public static void creariAfegirDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
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


    /**
     * Pre: - Post:	Retorna un boolea que indica si existeix el doctor o no
     */
    public static boolean existeixDoctor(String dni) {
        Doctor d = new Doctor(dni);
        //Creem un Dummy i comprovem si existeix.
        return Hospital.getHospital_dni().contains(d);
    }

    /**
     * Pre: -
     * Post: S'ha eliminat el doctor de l'hospital.
     */
    public static void eliminarDoctor(String dni) {
        Doctor doc = new Doctor(dni);
        if (CtrlHospital.existeixDoctor(dni)){
            //L'esborrem de l'arbre de DNIs
            Doctor aux = CtrlHospital.getDoctor(dni);
            Hospital.getHospital_dni().remove(aux);
            Hospital.getHospital_nom().remove(aux);
        }
    }

    /**
     * Pre: Doctor ha d'existir a l'hospital
     * Post: Retorna el doctor
     *
     */
    public static Doctor getDoctor(String dni){
        return (Doctor) Hospital.getHospital_dni().ceiling(new Doctor(dni));
    }

    /**
     * Retorna el núm. total de doctors existents a l'hospital
     * @return
     */
    public static int numDocs(){ return Hospital.getHospital_dni().size();}

    /**
     * Retorna l'hospital (conjunt de doctors) ordenat per cognom1, cognom2, nom i DNI
     * @return
     */
    public static TreeSet<Doctor> getHospital_nom(){
        return Hospital.getHospital_nom();
    }

    /**
     * Retorna l'hospital (conjunt de doctors) ordenat per DNI
     * @return
     */
    public static TreeSet<Doctor> getHospital_dni(){
        return Hospital.getHospital_dni();
    }


    // -------- Modificadors per a cada doctor ------------------

    /**
     * Modifica tots els atributs d'un doctor amb dni = d amb els nous.
     * (Nom, cognoms, sou, telèfon i correu electrònic)
     * @param d
     * @param n
     * @param cg1
     * @param cg2
     * @param s
     * @param t
     * @param c
     */
    public static void modificaAtributs(String d, String n, String cg1, String cg2, int s, int t, String c){
        Doctor doc = getDoctor(d);

        //REORDENEM
        TreeSet<Doctor> ll = getHospital_nom();
        ll.remove(doc);

        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setTelefon(t);
        doc.setSou(s);
        doc.setCorreu(c);

        ll.add(doc);
    }

    /**
     * Retorna strings dels doctors que hi ha a l'hospital ordenats per nom
     * @return
     */
    public static String getLlistatDoctorsenString_nom(){
        String content = "";
        TreeSet<Doctor> ll = CtrlHospital.getHospital_nom();
        for (Doctor doc:ll) {
            content = content + doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " "
                    + doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + "\n";
        }

        return content;
    }

    /**
     * Retorna tota la informació del doctor amb dni = dni
     * @param dni
     * @return
     */
    public static String getDoctorEspecificString(String dni){
        Doctor doc = getDoctor(dni);
        return doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + " \n";

    }

    /**
     * Retorna en un String (per a la capa de presentació) que conté tots els doctors amb tota la
     * informació de cadascún d'ells
     * @return
     */
    public static String getDoctorsSensePlt() {
        String content ="";
        TreeSet<Doctor> ll = CtrlHospital.getHospital_nom();
        for(Doctor doc: ll) {
            if(!doc.isActiu()) {
                content = content + doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " "
                        + doc.getCognom2() + "\n";
            }
        }
        return content;
    }


    /**
     * Guarda tots els doctors
     */
    public static void guardar(File f) {
    	String content = "";
    	TreeSet<Doctor> ll = CtrlHospital.getHospital_dni();
    	for (Doctor doc:ll) {
    		content = content + doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " "
    				+ doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + "\n";
    	}
    	CtrlPersistencia.guardar(content, f);
    }

    /**
     * Carrega els doctors del fitxer f
     * @param f
     */
    public static void carregar(File f) {
    	String content = CtrlPersistencia.carregar(f);
    	String separadors = "[ \n]";
    	String[] separat = content.split(separadors);
    	for (int i = 0; i < separat.length; i += 7) {
    		creariAfegirDoctor(separat[i],separat[i+1],separat[i+2],separat[i+3],Integer.parseInt(separat[i+4]), Integer.parseInt(separat[i+5]), separat[i+6]);
    	}
    }

}
