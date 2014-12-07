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

