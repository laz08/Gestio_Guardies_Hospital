package prop;

public class PushRelabel extends Algorisme {
	Graf G;
	int[] altura;
	int[] exces;
	Queue<Integer> q;
	Boolean[] esta;
	int ZERO = 0;
	
	PushRelabel(Graf GG) {
		G = GG;
	}
	
	public int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	
	public void preflow(int s, int t) {
		int n = G.numV();
		altura = new int[n];
		exces = new int[n];
		
		for (int i = 0; i < n; ++i) {
			altura[i] = 0;
			exces[i] = ZERO;
			esta[i] = false;
		}
		
		G.resetFlow();
		
		altura[s] = n;
		List<Integer> L = G.adjacents(s);
        Iterator<Integer> it = L.iterator();
		
		while (it.hasNext()) {
			int e = (Integer) it.next();
			Aresta arista = G.getAresta(e);
			if (s == arista.getw()) continue;
			int v = arista.contrari(s);
			arista.addresflow(v, arista.capres(s));
			exces[v] = exces[v].add(arista.getflow());
			exces[s] = exces[s].subtract(arista.getflow());
			if (v != t) {
				esta[v] = true;
				q.add(v);
			}
		}
	}
	
	public void push(int u, int e) {
		Edge a = G.getAresta(e);
		int aux = min(exces[u], a.capres(u));
		int v = a.contrari(u);
		arista.addresflow(v, aux);
		exces[u] = exces[u].subtract(aux);
		exces[v] = exces[v].add(aux);
	}	
	
	public int maxFlow(int s, int t) {
		
		Buscador B = new Buscador(G);
		int[] cami = new int[G.getNumA()];
		if (!B.BFS(s, t, cami)) return ZERO;
		
		q = new LinkedList<Integer>();
		esta = new Boolean[G.getNumA()];
		preflow(s, t);
		
		while(!q.isEmpty()) {
			
            int u = (Integer) q.peek();
            List<Integer> L = G.adjacents(u);
            Iterator<Integer> it = L.iterator();
            int nou = -1;
            
            while (it.hasNext() && exces[u] > 0) {
            	int e = (Integer) it.next();
            	Edge arista = G.getAresta(e);
            	int v = arista.contrari(u);
            	
            	if (arista.capres(u) > 0) {
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