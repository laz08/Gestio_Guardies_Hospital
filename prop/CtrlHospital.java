
package prop;

import java.util.Collections;
import java.util.Comparator;

public class CtrlHospital {



    public static Doctor crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor();
        doc.setdni(d);
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
     * Pre: El doctor no existeix a l'hospital.
     * Post: S'ha afegit el doctor a l'hospital en la posició alfabètica que li correspon
     */
    public static void creariAfegirDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor();
        doc.setdni(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu

        Hospital.getHospital().add(doc);
        Collections.sort(Hospital.getHospital(), new Comparator<Doctor>() {
            @Override
            public int compare(Doctor doctor, Doctor doctor2) {
                return doctor.getdni().compareTo(doctor2.getdni());
            }
        });
    }


    /**
     * Pre: - Post:	Retorna posició positiva si existeix el doctor dins l'hospital.
     * Altrament, retorna -1 per indicar que no existeix.
     */
    public static int existeixDoctor(String dni) {
        Doctor d;
        for (int i = 0; i < Hospital.getHospital().size(); ++i) {
            d = (Doctor) Hospital.getHospital().get(i);
            if (d.getdni().equals(dni)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pre: "pos" és una posició vàlida
     * Post: S'ha eliminat el doctor en la posició pos de l'hospital
     */
    public static void eliminarDoctor(int pos) {
       Hospital.getHospital().remove(pos);
    }




    /**
     * Pre: Doctor ha d'existir a l'hospital en la posició pos.
     * Post: Retorna el doctor que està a la posició pos.
     *
     */
    public static Doctor getDoctor(int pos) {
        return (Doctor) Hospital.getHospital().get(pos);
    }

    public static int numDocs(){ return Hospital.getHospital().size();}


    // -------- Modificadors per a cada doctor ------------------

    public static void modificarNom(Doctor doc, String nom){
        doc.setNom(nom);
    }

    public static void modificarCognom1(Doctor doc, String cg1){
        doc.setCognom1(cg1);
    }

    public static void modificarCognom2(Doctor doc, String cg2){
        doc.setCognom2(cg2);
    }

    public static void modificarTelf(Doctor doc, int telf){
        doc.setTelefon(telf);
    }

    public static void modificarCorreu(Doctor doc, String mail){
        doc.setCorreu(mail);
    }

    public static void modificarSou(Doctor doc, int sou){
        doc.setSou(sou);
    }

    public static void modificarActivitat(Doctor doc, boolean actiu){
        doc.setActiu(actiu);
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

}
