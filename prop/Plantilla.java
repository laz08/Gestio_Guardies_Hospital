package prop;

import java.util.ArrayList;

public class Plantilla {

    private String nom_p;
    private ArrayList<Doctor> P;

    public Plantilla(){

    }

    public Plantilla(String nom) {
        String nom_p = nom;
        P = new ArrayList<Doctor>();
    }

    /**
     * Pre:	- Post:	Retorna el nom de la plantilla.
     */
    public String getNomPlantilla() {
        return nom_p;
    }

    /**
     * Pre:	- Post:	Retorna el punter a l'array que conté tota la plantilla.
     */
    public ArrayList<Doctor> getLlistaDoctors() {
        return P;
    }

    /**
     * Assigna un nom a la plantilla
     * @param nom Nom que es vol posar a la plantilla
     */
    public void setNomPlantilla(String nom) {
        nom_p = nom;
    }
    
}
