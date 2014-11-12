package prop;

//Classe molt primitiva, falta arreclar tot el tema de les restriccions primer
class Graf { 
	int V, E, numa = 0;
	List<Integer>[] adj;
	Aresta[] As;
	int inifinit = Integer.MAX_VALUE;
	int zero = 0;
	
	public Graf(int nV, int nA) {
		V = nV;
		E = nA;
		As = new Aresta[nA];
		adj = new List[V];
		for (int i = 0; i < V; ++i) {
			adj[i] = new ArrayList<intger>();
		}
	}
	
	public int afegirAresta(int a, int b, long long int capacitat, long long int flow) {
        if (numa >= E) return -1;
        Aresta[numa] = new Aresta(a, b, capacitat, flow);
        adj[a].add(numa);
        adj[b].add(numa);
        return numa++;
    }
	
	public int getNumA() {
		return E;
	}
	
	public int getNumV() {
		return V;
	}
	
	public List<int> adjacents(int v) {
		return adj[v];
	}
	
	public resetFlow(int e) {
		for (int i = 0; i < E; ++i) {
			As[].resetflow();
		}
	}
}