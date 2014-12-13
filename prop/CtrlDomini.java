package prop;

import java.util.TreeSet;

public class CtrlDomini {


    //CASOS D'US


    //-------------HOSPITAL-------------

    public static boolean existeixDoctoraHospital(String d) {
        return CtrlHospital.existeixDoctor(d);
    }

    public static void creaDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail) {
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, telf, mail);
    }
    public static Doctor consultaDoctor(String dni){
        return CtrlHospital.getDoctor(dni);
    }
    public static TreeSet<Doctor> consultaHospital_dni(){
        return CtrlHospital.getHospital_dni();
    }

    public static TreeSet<Doctor> consultaHospital_nom(){
        return CtrlHospital.getHospital_nom();
    }



    //-------------PLANTILLA-------------




    //-------------RESTRICCIONS-------------

    //-------------CALENDARI-------------

}