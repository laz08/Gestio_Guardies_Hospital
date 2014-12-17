
package prop;

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

    public static int numDocs(){ return Hospital.getHospital_dni().size();}

    public static TreeSet<Doctor> getHospital_nom(){
        return Hospital.getHospital_nom();
    }

    public static TreeSet<Doctor> getHospital_dni(){
        return Hospital.getHospital_dni();
    }


    // -------- Modificadors per a cada doctor ------------------

    public static void modificarNom(Doctor doc, String nom){
        doc.setNom(nom);
    }
    public static void modificarNomD(String d, String nom){
        Doctor doc = getDoctor(d);
        doc.setNom(nom);
    }


    public static void modificarCognom1(Doctor doc, String cg1){
        doc.setCognom1(cg1);
    }
    public static void modificarCognom1D(String d, String cg1){
        Doctor doc = getDoctor(d);
        doc.setCognom1(cg1);
    }

    public static void modificarCognom2(Doctor doc, String cg2){
        doc.setCognom2(cg2);
    }
    public static void modificarCognom2D(String d, String cg2){
        Doctor doc = getDoctor(d);
        doc.setCognom2(cg2);
    }

    public static void modificarTelf(Doctor doc, int telf){
        doc.setTelefon(telf);
    }
    public static void modificarTelfD(String d, int telf){
        Doctor doc = getDoctor(d);
        doc.setTelefon(telf);
    }

    public static void modificarCorreu(Doctor doc, String mail){
        doc.setCorreu(mail);
    }
    public static void modificarCorreuD(String d, String correu){
        Doctor doc = getDoctor(d);
        doc.setCorreu(correu);
    }

    public static void modificarSou(Doctor doc, int sou){
        doc.setSou(sou);
    }
    public static void modificarSouD(String d, int sou){
        Doctor doc = getDoctor(d);
        doc.setSou(sou);
    }

    public static void modificarActivitat(Doctor doc, boolean actiu){
        doc.setActiu(actiu);
    }
    public static void modificarActivitatD (String d, boolean actiu){
        Doctor doc = getDoctor(d);
        doc.setActiu(actiu);
    }

    public static void modificaAtributs(String d, String n, String cg1, String cg2, int s, int t, String c){
        Doctor doc = getDoctor(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setTelefon(t);
        doc.setSou(s);
        doc.setCorreu(c);
    }


    // -------- Consultors per a cada doctor ------------------
    public static String consultarNom(Doctor doc){
        return doc.getNom();
    }

    public static String consultarCognom1(Doctor doc){
        return doc.getCognom1();

    }

    public static String consultarCognom2(Doctor doc){
        return doc.getCognom2();
    }

    public static String consultarDni(Doctor doc){
        return doc.getdni();
    }

    public static int consultarSou(Doctor doc){
        return doc.getSou();
    }

    public static int consultarTelefon(Doctor doc){
        return doc.getTelefon();
    }

    public static String consultarCorreu(Doctor doc){
        return doc.getCorreu();
    }

    public static boolean consultarActivitat(Doctor doc){
        return doc.isActiu();
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
    public static String getDoctorEspecificString(String dni){
        Doctor doc = getDoctor(dni);
        return doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " " + doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + " \n";

    }

    public static void guardar() {
    	String content = "";
    	TreeSet<Doctor> ll = CtrlHospital.getHospital_dni();
    	for (Doctor doc:ll) {
    		content = content + doc.getdni() + " " + doc.getNom() + " " + doc.getCognom1() + " "
    				+ doc.getCognom2() + " " + doc.getSou() + " " + doc.getTelefon() + " " + doc.getCorreu() + "\n";
    	}
    	CtrlPersistencia.guardar(content, "hospital_dni");
    }

    public static void carregar() {
    	String content = CtrlPersistencia.carregar("hospital_dni");
    	String separadors = "[ \n]";
    	String[] separat = content.split(separadors);
    	for (int i = 0; i < separat.length; i += 7) {
    		creariAfegirDoctor(separat[i],separat[i+1],separat[i+2],separat[i+3],Integer.parseInt(separat[i+4]), Integer.parseInt(separat[i+5]), separat[i+6]);
    	}
    }

}
