package prop;

import java.util.ArrayList;

public class Hospital {

    private ArrayList H;

    public Hospital() {
        H = new ArrayList<Doctor>();
    }

    /**
     * Pre: El doctor no existeix a l'hospital. Post:	S'ha afegit el doctor a
     * l'hospital.
     */
    public void afegirDoctor(Doctor d) {
        H.add(d);
    }

    /**
     * Pre: "pos" és una posició vàlida Post:	S'ha eliminat el doctor en la
     * posició pos de l'hospital
     */
    public void eliminarDoctor(int pos) {
        H.remove(pos);
    }

    /**
     * Pre: - Post:	Retorna posició positiva si existeix el doctor dins l'hospital.
     * Altrament, retorna -1 per indicar que no existeix.
     */
    public int existeixDoctor(String dni) {
        Doctor d;
        for (int i = 0; i < H.size(); ++i) {
            d = (Doctor) H.get(i);
            if (d.getdni() == dni) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pre: Doctor ha d'existir a l'hospital en la posició pos.
     * Post: Retorna el doctor que està a la posició pos.
     *
     */
    public Doctor getDoctor(int pos) {
        return (Doctor) H.get(pos);
    }
}
