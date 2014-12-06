package prop;

import java.util.Comparator;
import java.util.TreeSet;

public class Hospital {


    private static TreeSet<Doctor> H_dni  = new TreeSet<Doctor>(new compDni());
    private static TreeSet<Doctor> H_nom = new TreeSet<Doctor>(new compNom());
    //private static ArrayList<Doctor> H = new ArrayList<Doctor>();

    /**
     * Retorna l'hospital.
     * @return H
     */
    public static TreeSet<Doctor> getHospital_dni(){
        return H_nom;
    }
    public static TreeSet<Doctor> getHospital_nom(){
        return H_dni;
    }


    //LAURA: SetHospital fins ara no l'hem usat mai. Si és prescindible, l'esborraré.
    /*
    public static void setHospital(ArrayList<Doctor> L){
        H = L;
    }
    */



}
 class compDni implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doc1, Doctor doc2){
        return doc1.getdni().compareTo(doc2.getdni());
    }
}

class compNom implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doc1, Doctor doc2){
        return doc1.getNom().compareTo(doc2.getNom());
    }
}
