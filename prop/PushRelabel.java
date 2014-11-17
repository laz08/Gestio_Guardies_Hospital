public class PushRelabel extends Algorisme {
	Graf G;
	int[] altura;
	int[] exces;
	Queue<Integer> q;
	Boolean[] esta;
	int ZERO = 0;
	
	PushRelabel(Graph GG) {
		G = GG;
	}
	
	public int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	
	public void inicialitza(int s, int t) {
		int n = G.numeroVertex();
		altura = new int[n];
		exces = new int[n];
		
		for (int i = 0; i < n; ++i) {
			altura[i] = 0;
			exces[i] = ZERO;
			esta[i] = false;
		}
		
		G.borraFlow();
		
		altura[s] = n;
		List<Integer> L = G.adyacentes(s);
        Iterator<Integer> it = L.iterator();
		
		while (it.hasNext()) {
			int e = (Integer) it.next();
			Aresta arista = G.getEdge(e);
			if (s == arista.extremDret()) continue;
			int v = arista.vei(s);
			arista.augmentaFlow(v, arista.cap(s));
			exces[v] = exces[v].add(arista.getFlow());
			exces[s] = exces[s].subtract(arista.getFlow());
			if (v != t) {
				esta[v] = true;
				q.add(v);
			}
		}
	}
	
	public void push(int u, int e) {
		Edge arista = G.getEdge(e);
		int aux = min(exces[u], arista.cap(u));
		int v = arista.vei(u);
		arista.augmentaFlow(v, aux);
		exces[u] = exces[u].subtract(aux);
		exces[v] = exces[v].add(aux);
	}	
	
	public BigInteger maxFlow(int s, int t) {
		
		Buscador B = new Buscador(G);
		int[] cami = new int[G.numeroVertex()];
		if (!B.BFS(s, t, cami)) return ZERO;
		
		q = new LinkedList<Integer>();
		esta = new Boolean[G.numeroVertex()];
		inicialitza(s, t);
		
		while(!q.isEmpty()) {
			
            int u = (Integer) q.peek();
            List<Integer> L = G.adyacentes(u);
            Iterator<Integer> it = L.iterator();
            int nou = -1;
            
            while (it.hasNext() && exces[u] > 0) {
            	int e = (Integer) it.next();
            	Edge arista = G.getEdge(e);
            	int v = arista.vei(u);
            	
            	if (arista.cap(u) > 0) {
            		
            		if (altura[u] == altura[v] + 1) {
            			push(u, e);
            			if (!esta[v] && v != t && v != s) {
            				esta[v] = true;
            				q.add(v);
            			}
            			
            		}
            		else {
            			if(nou == -1) nou = altura[v];
            			nou = min(nou, altura[v]);
            		}
            		
            	}
            }
            
            if (exces[u] > 0) altura[u] = nou + 1;
            else {
            	esta[u] = false;
            	q.remove();
            }
            
        }
        return exces[t];
	}

}