package prop;

//Classe molt primitiva, falta arreclar tot el tema de les restriccions primer

import java.util.ArrayList;

class Graf { 
	int V, E, numa = 0;
	ArrayList<Integer>[] adj;
	Aresta[] As;
	int inifinit = Integer.MAX_VALUE;
	int zero = 0;
	
	public Graf(int nV, int nA) {
		V = nV;
		E = nA;
		As = new Aresta[nA];
		adj = new ArrayList[V];
		for (int i = 0; i < V; ++i) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public int afegirAresta(int a, int b, int capacitat, int flow) {
        if (numa >= E) return -1;
        As[numa] = new Aresta(a, b, capacitat, flow);
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
	
	public ArrayList<Integer> adjacents(int v) {
		return adj[v];
	}
	
	public void resetFlow(int e) {
		for (int i = 0; i < E; ++i) {
			As[i].resetflow();
		}
	}
}