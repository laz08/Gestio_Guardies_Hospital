package prop;


import java.util.ArrayList;

public class Graf{
	private int numa = 0;
	private ArrayList<Aresta> As;
    private ArrayList<Vertex> Vs;
	public final static int INFINIT = Integer.MAX_VALUE;
	public int ZERO = 0;

	
	public Graf() {
		As = new ArrayList<Aresta>();
		Vs = new ArrayList<Vertex>();
	}
	
  	public void afegirAresta(Vertex v1, Vertex v2, int capacitat, int flow) {
		int a = Vs.indexOf(v1);
        int b = Vs.indexOf(v2);
        Aresta ar = new Aresta(a, b, capacitat, flow);
        As.add(ar);
        Vs.get(a).afegir_aresta(As.indexOf(ar));
        Vs.get(b).afegir_aresta(As.indexOf(ar));
        numa++;
    }
        
    public void afegirVertex(Vertex v){
    	if(!Vs.contains(v)) Vs.add(v);
    }
        
    public int getNumA() {
		return As.size();
	}
    
    public Aresta getA(int a) {
    	return As.get(a);
    }
	
	public int numV() {
		return Vs.size();
	}
	
    public Vertex getVertex(String id, int c) {
        Vertex v= null;
        boolean trobat = false;
        int pos = 0;
        while (!trobat && pos<Vs.size()){
            v = Vs.get(pos);
            if(v.getClasse() == c && v.getId().equals(id)) trobat = true;
            pos ++;
        }
        return v;
    }
        
	public ArrayList<Integer> adjacents(int v) {
		return Vs.get(v).getArestes();
	}
	
	public void resetFlow() {
		for (int i = 0; i < As.size(); ++i) {
			As.get(i).resetflow();
		}
	}
}