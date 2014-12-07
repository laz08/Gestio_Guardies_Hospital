package prop;


import java.util.TreeSet;

public class Plantilla {

    private String nom_p;
    //private ArrayList<Doctor> P;
    private TreeSet<Doctor> P_nom;
    private TreeSet<Doctor> P_dni;
    private int id_calendari_asoc;


    /**
     * Constructor.
     * Crea una nova instància del tipus Plantilla amb nom_p = nom.
     * @param nom
     */
    public Plantilla(String nom) {
        nom_p = nom;
        P_nom = new TreeSet<Doctor>(new compNom());
        P_dni = new TreeSet<Doctor>(new compDni());
        id_calendari_asoc = -1;     //-1 indica que no té cap calendari associat
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
     * @param idC
     */
    public void setId_calendari_asoc(int idC){
        id_calendari_asoc = idC;
    }

    /**
     * Retorna l'identificador del calendari associat a la plantilla
     * @return id_calendari_asoc
     */
    public int getId_calendari_asoc(){
        return id_calendari_asoc;
    }



}


