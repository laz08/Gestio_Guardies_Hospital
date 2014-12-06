package prop;

import java.util.ArrayList;

public class Graf {

    private int numa = 0;
    private ArrayList<Aresta> As;
    private ArrayList<Vertex> Vs;
    public final static int INFINIT = Integer.MAX_VALUE;
    public int ZERO = 0;
    
    /**
     * Graf format per una llista de vertex i una llista de arestes
     */
    public Graf() {
        As = new ArrayList<Aresta>();
        Vs = new ArrayList<Vertex>();
    }

    /**
     * Crea una aresta entre 2 vertex ja existents i la enmagatzema a la llista de arestes
     * @param v1 Primer vertex de l'aresta
     * @param v2 Segon vertex de l'aresta
     * @param capacitat Capacitat de l'aresta
     * @param flow flux de l'aresta
     */
    public void afegirAresta(Vertex v1, Vertex v2, int capacitat, int flow) {
        int a = Vs.indexOf(v1);
        int b = Vs.indexOf(v2);
        Aresta ar = new Aresta(a, b, flow, capacitat);
        if (!As.contains(ar)) As.add(ar);
        Vs.get(a).afegir_aresta(As.indexOf(ar));
        Vs.get(b).afegir_aresta(As.indexOf(ar));
        numa++;
    }

    public void eliminaAresta(int pos){
        if(pos<As.size()) As.remove(pos);
        for(int i=0; i<Vs.size(); i++){
            Vertex v = Vs.get(i);
            ArrayList<Integer> arestes = v.getArestes();
            int e=0;
            boolean trobat = false;
            while (e < arestes.size() && !trobat){
                int numA = arestes.get(e);
                if(numA == pos){
                    v.emimina_aresta(e);
                    trobat = true;
                }
                e++;
            }
        }
    }
    
    /**
     * afageix un nou vertex al graf
     * @param v Vertex que es vol afagir
     */
    public void afegirVertex(Vertex v) {
        if (!Vs.contains(v)) {
            Vs.add(v);
        }
    }

    /**
     * Retorna el nombre d'arestes que conté el graf
     * @return nombre de arestes
     */
    public int getNumA() {
        return As.size();
    }

    /**
     * Retorna el nombre de vertex que conté el graf
     * @return nombre de vertex
     */
    public int numV() {
        return Vs.size();
    }
    
    /**
     * Donat un nombre, retorna l'aresta que correspon a aquella posició dins la llista d'arestes
     * @param posA posicio de l'aresta
     * @return 
     */
    public Aresta getA(int posA) {
        return As.get(posA);
    }
 
    /**
     * Donat un nombre, retorna el vertex que correspon a aquella posició dins la llista de vertex
     * @param i posicio del vertex
     * @return 
     */
    public Vertex getVertex(int posX){
        return Vs.get(posX);
    }
    
    /**
     * Donat un identificador de vertex, format per un String i la classe del vertex retorna l'objecte en cuestió
     * @param id String identificador dins la classe de vertex
     * @param c Classe de vertex
     * @return 
     */
    public Vertex getVertex(String id, int c) {
        Vertex v = null;
        boolean trobat = false;
        int pos = 0;
        while (!trobat && pos < Vs.size()) {
            v = Vs.get(pos);
            if (v.getClasse() == c && v.getId().equals(id)) {
                trobat = true;
            }
            pos++;
        }
        return v;
    }

    /**
     * Retorna la posició dels vertex adjacents al vertex dons la llista de vertex
     * @param v Posició del vertex del que es vol saber els adjacents
     * @return 
     */
    public ArrayList<Integer> getAdjacents(int v) {
        Vertex vertex = Vs.get(v);
        ArrayList<Integer> ar = vertex.getArestes();
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        Aresta a;
        for(int i=0; i < ar.size(); i++){
            a = As.get(ar.get(i)); // accedeix a l'aresta a la que fa referència l'integer de la llista ar
            int v1 = a.getv();
            int v2 = a.getw();
            if (v1 == v) adjacents.add(v2);
            else if(v2 == v) adjacents.add(v1);
        }
        return adjacents;
    }

    /**
     * Posa tot el flux a 0
     */
    public void resetFlow() {
        for (int i = 0; i < As.size(); ++i) {
            As.get(i).resetflow();
        }
    }
}