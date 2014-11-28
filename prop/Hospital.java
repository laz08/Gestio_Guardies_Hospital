package prop;

import java.util.ArrayList;

public class Hospital {

    private static ArrayList<Doctor> H = new ArrayList<Doctor>();

    /**
     * Retorna l'hospital.
     * @return H
     */
    public static ArrayList<Doctor> getHospital(){
        return H;
    }

    /*
    public static void setHospital(ArrayList<Doctor> L){
        H = L;
    }
    */

}
