package prop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hospital {

    private ArrayList<Doctor> H;

    public Hospital() {
        H = new ArrayList<Doctor>();
    }


    /**
     * Pre: El doctor no existeix a l'hospital.
     * Post: S'ha afegit el doctor a l'hospital en la posició alfabètica que li correspon
     */
    public void creariAfegirDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor();
        doc.setdni(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu
        H.add(doc);
        Collections.sort(H, new Comparator<Doctor>() {
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
    public int existeixDoctor(String dni) {
        Doctor d;
        for (int i = 0; i < H.size(); ++i) {
            d = (Doctor) H.get(i);
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
    public void eliminarDoctor(int pos) {
        H.remove(pos);
    }




    /**
     * Pre: Doctor ha d'existir a l'hospital en la posició pos.
     * Post: Retorna el doctor que està a la posició pos.
     *
     */
    public Doctor getDoctor(int pos) {
        return (Doctor) H.get(pos);
    }

    public int numDocs(){ return H.size();}


    // -------- Modificadors per a cada doctor ------------------
    public void modificarNom(Doctor doc, String nom){
        doc.setNom(nom);
    }

    public void modificarCognom1(Doctor doc, String cg1){
        doc.setCognom1(cg1);
    }

    public void modificarCognom2(Doctor doc, String cg2){
        doc.setCognom2(cg2);
    }

    public void modificarTelf(Doctor doc, int telf){
        doc.setTelefon(telf);
    }

    public void modificarCorreu(Doctor doc, String mail){
        doc.setCorreu(mail);
    }

    public void modificarSou(Doctor doc, int sou){
        doc.setSou(sou);
    }

    public void modificarActivitat(Doctor doc, boolean actiu){
        doc.setActiu(actiu);
    }


    // -------- Consultors per a cada doctor ------------------
    public String consultarNom(Doctor doc){
        return doc.getNom();
    }

    public String consultarCognom1(Doctor doc){
        return doc.getCognom1();

    }

    public String consultarCognom2(Doctor doc){
        return doc.getCognom2();
    }

    public String consultarDni(Doctor doc){
        return doc.getdni();
    }

    public int consultarSou(Doctor doc){
        return doc.getSou();
    }

    public int consultarTelefon(Doctor doc){
        return doc.getTelefon();
    }

    public String consultarCorreu(Doctor doc){
        return doc.getCorreu();
    }

    public boolean consultarActivitat(Doctor doc){
        return doc.isActiu();
    }


}
