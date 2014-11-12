package prop;

import java.util.ArrayList;

public class Plantilla {

    private String nom_p;
    private ArrayList p;

    public Plantilla(String nom) {
        String nom_p = nom;
        p = new ArrayList<Doctor>();
    }

    /**
     * Pre:	- Post:	Retorna el nom de la plantilla.
     */
    public String get_nom_Plantilla() {
        return this.nom_p;
    }

    /**
     * Pre:	- Post:	Retorna el punter a l'array que conté tota la plantilla.
     */
    public ArrayList get_llista_doctors() {
        return this.p;
    }

    /**
     * Assigna un nom a la plantilla
     * @param nom Nom que es vol posar a la plantilla
     */
    public void setNomPlantilla(String nom) {
        this.nom_p = nom;
    }
    
}
