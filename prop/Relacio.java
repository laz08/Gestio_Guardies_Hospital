package prop;

import java.util.ArrayList;
public class Relacio {

    private ArrayList<Relacio> relacionats;
    private Object objecte;

    public Relacio(Object o) {
        relacionats = new ArrayList<Relacio>();
        objecte = o;
    }
    /**
     * Afegeix una relacio al conjunt de relacions.
     * @param r
     */
    public void addRelacio(Relacio r) {
        if (!relacionats.contains(r)) {
            relacionats.add(r);
        }
    }
    
    /**
     * Relaciona un objecte amb al passat
     * per parametre.
     * @param o
     */
    public void setObj(Object o) {
        objecte = o;
    }

    /**
     * Retorna l'objecte relacionat
     * @return
     */
    
    public Object getObj() {
        return objecte;
    }
    
    /**
     * Retorna tota la llista de relacions.
     * @return
     */
    
    public ArrayList<Relacio> getRelacionats() {
        ArrayList<Relacio> rel = new ArrayList<Relacio>();
        for (int i = 0; i < relacionats.size(); i++) {
            rel.add(relacionats.get(i));
        }
        return rel;
    }
    
    /**
     * Elimina una relacio.
     * @param o objecte a eliminar la relacio.
     */
    
    public void rmRelacio(Object o){
        for(int i=0; i<relacionats.size(); i++){
            Relacio r = relacionats.get(i);
            if (r.getObj().equals(o)){
                relacionats.remove(r);
            }
        }
    }
}
