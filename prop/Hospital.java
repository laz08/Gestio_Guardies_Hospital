package prop;

import java.util.ArrayList;

public class Hospital {

    private ArrayList<Doctor> H;

    public Hospital() {
        H = new ArrayList<Doctor>();
    }

    public ArrayList<Doctor> getHospital(){
        return H;
    }

    public void setHospital(ArrayList<Doctor> L){
        H = L;
    }

}
