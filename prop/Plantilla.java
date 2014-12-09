package prop;


import java.util.TreeSet;

public class Plantilla {

    private String nom_p;
    //private ArrayList<Doctor> P;
    private TreeSet<Doctor> P_nom;
    private TreeSet<Doctor> P_dni;
    private Calendari C;


    /**
     * Constructor.
     * Crea una nova instància del tipus Plantilla amb nom_p = nom.
     * @param nom
     */
    public Plantilla(String nom) {
        nom_p = nom;
        P_nom = new TreeSet<Doctor>(new compNom());
        P_dni = new TreeSet<Doctor>(new compDni());
        C = null;
    }

    /**
     * Pre:	- Post:	Retorna el nom de la plantilla.
     */
    public String getNomPlantilla() {
        return nom_p;
    }

    /**
     * Pre:	- Post:	Retorna el punter al TreeSet que conté tota la plantilla ordenada per DNI
     */
    public TreeSet<Doctor> getLlistaDoctorsDNI() {
        return P_dni;
    }

    public TreeSet<Doctor> getLlistaDoctorsNom(){
        return P_nom;
    }

    /**
     * Assigna un nom a la plantilla
     * @param nom Nom que es vol posar a la plantilla
     */
    public void setNomPlantilla(String nom) {
        nom_p = nom;
    }

    /**
     * Assigna l'identificador del calendari associat a la plantilla
     * @param calendari_asoc
     */
    public void set_calendari_asoc(Calendari calendari_asoc){
      C = calendari_asoc;
    }

    /**
     * Retorna l'identificador del calendari associat a la plantilla
     * @return id_calendari_asoc
     */
    public Calendari get_calendari_asoc(){
        return C;
    }



}


