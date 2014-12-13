package prop;

import java.util.TreeSet;

public class CtrlDomini {


    //CASOS D'US


    //-------------HOSPITAL-------------

    public static boolean existeixDoctoraHospital(String d) {
        return CtrlHospital.existeixDoctor(d);
    }

    /**
     * PRE: No existeix cap doctor amb dni = d a l'hospital
     * POST: S'ha creat doctor amb tots els paràmetres a l'hospital
     * @param d
     * @param n
     * @param cg1
     * @param cg2
     * @param s
     * @param telf
     * @param mail
     */
    public static void creaDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail) {
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, telf, mail);
    }
    public static Doctor consultaDoctor(String dni){
        return CtrlHospital.getDoctor(dni);
    }

    /**
     * PRE: Existeix doctor amb aquest dni a l'hospital.
     * POST: Queda esborrat doctor de l'hospital
     * @param dni
     */
    public static void eliminaDoctorHospital(String dni){
        CtrlHospital.eliminarDoctor(dni);
    }
    public static TreeSet<Doctor> consultaHospital_dni(){
        return CtrlHospital.getHospital_dni();
    }
    public static TreeSet<Doctor> consultaHospital_nom(){
        return CtrlHospital.getHospital_nom();
    }
    public static void guardarDoctors(){
        CtrlHospital.guardar();
    }
    public static void carregarDoctors(){
        CtrlHospital.carregar();
    }


    //-------------PLANTILLA-------------

    public static boolean existeixPlantilla(String nom_p){
        return CtrlPlantilla.existeixPlantilla(nom_p);
    }
    public static void crearPlantilla(String nom_p){
        CtrlPlantilla.creariAfegirPlantilla(nom_p);
    }
    public static void esborraPlantilla(String nom_p){
        CtrlPlantilla.esborrarPlantilla(nom_p);
    }

    public static Plantilla consultaPlantilla(String nom_p){
        return CtrlPlantilla.consultarPlantilla(nom_p);
    }
    public static void afegirDoctorAPlantilla(Doctor doc, Plantilla plantilla){
        CtrlPlantilla.afegirDoctorAPlantilla(doc, plantilla);
    }
    public static void esborrarDoctorDePlantilla(Doctor doc, Plantilla plantilla){
        CtrlPlantilla.esborrarDoctorDePlantilla(doc, plantilla);
    }

    public static void associaCalendariPlantilla(Plantilla p, int any){
        CtrlPlantilla.crearCalendariAssociatAPlantilla(p, any);
    }
    public static void desassociaCalendariIEsborra(Plantilla p){
        CtrlPlantilla.desassociarDeCalendari(p);
    }
    public static void guardarPlantilles(){
        CtrlPlantilla.guardar();
    }
    public static void carregarPlantilles(){
        CtrlPlantilla.carregar();
    }
    public static TreeSet<Plantilla> llistarPlantilles(){
        return CtrlPlantilla.getCjt_plantilles();
    }


    //-------------RESTRICCIONS-------------

    //-------------CALENDARI-------------

    //-------------CARREGAR-------------
    public static void carregarGeneral(){
        CtrlPlantilla.carregar();
        CtrlHospital.carregar();
        CtrlCalendari.carregar();

    }

    //-------------GUARDAR-------------
    public static void guardarGeneral(){
        CtrlPlantilla.guardar();
        CtrlHospital.guardar();
        CtrlCalendari.guardar();
        CtrlRestriccio.guardar();
    }
}