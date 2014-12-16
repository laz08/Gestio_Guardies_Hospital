package prop;

import java.util.ArrayList;
public class Relacio {

    private ArrayList<Relacio> relacionats;
    private Object objecte;

    public Relacio(Object o) {
        relacionats = new ArrayList<Relacio>();
        objecte = o;
    }

    public void addRelacio(Relacio r) {
        if (!relacionats.contains(r)) {
            relacionats.add(r);
        }
    }

    public void setObj(Object o) {
        objecte = o;
    }

    public Object getObj() {
        return objecte;
    }

    public ArrayList<Relacio> getRelacionats() {
        ArrayList<Relacio> rel = new ArrayList<Relacio>();
        for (int i = 0; i < relacionats.size(); i++) {
            rel.add(relacionats.get(i));
        }
        return rel;
    }
    
    public void rmRelacio(Object o){
        for(int i=0; i<relacionats.size(); i++){
            Relacio r = relacionats.get(i);
            if (r.getObj().equals(o)){
                relacionats.remove(r);
            }
        }
    }
}
