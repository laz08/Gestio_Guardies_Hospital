
package prop;

import java.util.TreeSet;


public class CtrlPlantilla {

    private static TreeSet<Plantilla> Cjt_plantilles = new TreeSet<Plantilla>(new compNomP());
    private static Plantilla plantillaActual = null;


    // ------ PLANTILLES EN GENERAL ------

    //Pre: No existeix una plantilla amb nom = nom_plantilla
    //Post: Cjt_Plantilles ara té una nova plantilla amb el nom = nom_plantilla
    public static void creariAfegirPlantilla(String nom_plantilla){
        Plantilla p = new Plantilla(nom_plantilla);
        Cjt_plantilles.add(p);
    }

    //Pre:  -
    //Post: S'ha esborrat del conjunt de plantilles la plantilla amb nom nom_p
    public static void esborrarPlantilla(String nom_p){
        Plantilla p = new Plantilla(nom_p);
        //Creem Dummy i el passem
        Cjt_plantilles.remove(p);
    }

    //Pre:  -
    //Post: Retorna un booleà indicant si una plantilla al conjunt de plantilles amb el nom = nom_p existeix
    public static boolean existeixPlantilla(String nom_p){
        Plantilla aux = new Plantilla(nom_p);
        return Cjt_plantilles.contains(aux);
    }

    public static TreeSet<Plantilla> getCjt_plantilles(){
        return Cjt_plantilles;
    }

    // ------ PLANTILLA EN CONCRET ------

    //Pre: Existeix plantilla amb nom_p
    //Post: plantillaActual passa a ser la plantilla amb nom_p
    public static void setPlantillaActual(String nom_p){
       Plantilla p = new Plantilla(nom_p);
        p = Cjt_plantilles.ceiling(p);
        plantillaActual = p;
    }

    public static Plantilla getPlantillaActual(){
        return plantillaActual;
    }


    public static Plantilla consultarPlantilla(String nom_p){
        Plantilla p = new Plantilla(nom_p);
        return Cjt_plantilles.ceiling(p);
    }

    public static int num_plantilles(){
        return Cjt_plantilles.size();
    }




    /**
     *
     * @param dni Doctor que es vol introduir
     * @param nom_plantilla Plantilla a la que es vol introduir el doctor
     * pre: Doctor amb dni = dni existeix a l'hospital i no està a cap plantilla, existeix plantilla amb id = id_plantilla
     *          i també existeix doctor amb dni = dni
     * post: El doctor de l'hospital amb dni = dni pertany ara a la plantilla amb nom = id_plantilla
     */
    public static void afegirDoctorAPlantilla(String dni, String nom_plantilla){
        Doctor doc = CtrlHospital.getDoctor(dni);
        doc.setActiu(true);
        Plantilla p = Cjt_plantilles.ceiling(new Plantilla(nom_plantilla));
        p.getLlistaDoctorsDNI().add(doc);
        p.getLlistaDoctorsNom().add(doc);
    }


    //Pre: Doctor existeix i plantilla també, i doc està en la plantilla
    //Post: S'ha esborrat doctor de la plantilla indicada.
    public static void esborrarDoctordePlantilla(String dni, String id_plantilla){
        Doctor doc = CtrlHospital.getDoctor(dni);
        doc.setActiu(false);
        Plantilla p = Cjt_plantilles.ceiling(new Plantilla(id_plantilla));
        p.getLlistaDoctorsNom().remove(p);
        p.getLlistaDoctorsDNI().remove(p);
    }

    //Retorna si el doctor està assignat a una plantilla
    public static boolean docTePlantilla(String dni){
        return CtrlHospital.getDoctor(dni).isActiu();
    }


    //Pre: Existeix plantilla amb id = id_plantilla, existeix doctor amb dni = dni
    //Post: Retorna si el doctor està a la plantilla amb id_plantilla.
    public static boolean docEnPlantilla(String dni, String id_plantilla){
            Plantilla p = Cjt_plantilles.ceiling(new Plantilla(id_plantilla));
            return p.getLlistaDoctorsDNI().contains(new Doctor(dni));
    }

    public static void crearCalendariAssociataPlantillaActual(){
        // !!!!!!!!!! ================= !!!!!!!!!!!!!!!!!!!!!
        Calendari C = CtrlCalendari.CrearIAfegirCalendari(); //Hauria de retornar punter a aquest Calendari
        CtrlPlantilla.getPlantillaActual().set_calendari_asoc(C);
    }



}

