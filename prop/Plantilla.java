package prop;

import java.util.ArrayList;

public class Plantilla {

    private String nom_p;
    private ArrayList<Doctor> P;
    private int id_calendari_asoc;

    public Plantilla(){

    }

    public Plantilla(String nom) {
        nom_p = nom;
        P = new ArrayList<Doctor>();
        id_calendari_asoc = -1;
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

    public void setId_calendari_asoc(int idC){
        id_calendari_asoc = idC;
    }

    public int getId_calendari_asoc(){
        return id_calendari_asoc;
    }



}
