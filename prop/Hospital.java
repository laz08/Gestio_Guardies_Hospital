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
        return H_dni;
    }
    public static TreeSet<Doctor> getHospital_nom(){
        return H_nom;
    }
}


 class compDni implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doc1, Doctor doc2){
        if(doc1 == doc2) return 0;
        if(doc1.getdni() == null) return 1;
        if(doc2.getdni() == null) return -1;
        return doc1.getdni().compareTo(doc2.getdni());
    }
}

class compNom implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doc1, Doctor doc2){
        if (doc1 == doc2) return 0;
        if(doc1.getNom() == null) return 1;
        if(doc2.getNom() == null) return -1;
        return doc1.getNom().compareTo(doc2.getNom());
    }
}
