package prop;

public class Aresta {
	private int v, w;
	private int flow, capacitat;

    /**
     * Creadora d'aresta, del vertex v al vertex w, amb flow f i capacitat cap
     * @param vertexv
     * @param vertexw
     * @param f
     * @param cap
     */
	public Aresta(int vertexv, int vertexw, int f, int cap) {
		v = vertexv;
		w = vertexw;
		flow = f;
		capacitat = cap;
	}
	
	public Aresta(Aresta a) {
		v = a.v;
		w = a.w;
		flow = a.flow;
		capacitat = a.capacitat;
	}

    /**
     * Retorna el vèrtex origen
     * (Aresta v-w)
     * @return
     */
	public int getv() {
		return v;
	}

    /**
     * Retorna el vertex destí
     * (Aresta v-w)
     * @return
     */
	public int getw() {
		return w;
	}
	
	public Boolean full() {
		return flow == capacitat;
	}
	
	public void setv(int vertex) {
		v = vertex;
	}
	
	public void setw(int vertex) {
		w = vertex;
	}
	
	public void setflow(int f) {
		flow = f;
	}

    /**
     * Retorna el flow
     * @return
     */
	public int getflow() {
		return flow;
	}

    /**
     * Reseteja el flow
     */
	public void resetflow() {
		flow = 0;
	}

    /**
     * Retorna la capacitat de l'aresta
     * @return
     */
	public int getcap() {
		return capacitat;
	}

    /**
     * La capacitat de la aresta és c
     * @param c
     */
        public void setCap(int c){
            capacitat = c;
        }
        
	public int contrari(int ver) {
		if (ver == v) return w;
		else return v;
	}

    /**
     * Afegeix flow f
     * @param f
     */
	public void addFlow(int f) {
		flow += f;
	}
	
	public int capres(int vertex) {
		if (vertex == v) return capacitat-flow;
		else return flow;
	}
}