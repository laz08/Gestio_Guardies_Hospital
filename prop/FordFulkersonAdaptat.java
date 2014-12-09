package prop;

import java.util.*;

public class FordFulkersonAdaptat extends Algorisme {
	Graf G;
	Vertex[] cami;
	int INF = Integer.MAX_VALUE;

	public FordFulkersonAdaptat(Graf GG) {
		G = GG;
	}
	
	public int min(int a, int b) {
		if (a > b) return b;
		return a;
	}
	
	public Boolean BFS(int s, int t, int[] cami) {
		int N = G.numV();
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < N; ++i) cami[i] = -1;
		cami[s] = s;
		q.add(s);
		while (!q.isEmpty()) {
			int u = (Integer)q.remove();
			List<Integer> L = G.getAdjacents(u);
			Iterator<Integer> it = L.iterator();
			while (it.hasNext()) {
				int e = (Integer) it.next();
				Aresta a = G.getA(e);
				int v = a.contrari(u);
				if (a.capres(u)> 0 && cami[v] == -1) {
					cami[v] = e;
					q.add(v);
					if (v == t) return true;
				}
			}
		}
		return false;
	}
	
	public int augment(int s, int t) {
		if (s == t) return INF;
		Aresta a = G.getA(cami[t]);
		return min(a.capres(a.contrari(t)) , augment(s, a.contrari(t)) );
	}
	
	public void actualitzar(int s, int t, int increment) {
		if (s == t) return;
		Aresta a = G.getA(cami[t]);
		a.addresflow(t, increment);
		actualitzar(s, a.contrari(t), increment);
	}
	@Override
	public int maxFlow(int s, int t) {
		G.resetFlow();
		int flow = 0;
		cami = new int[G.numV()];
		while (BFS(s, t, cami)) {
			int increment = augment(s, t);
			flow += increment;
			actualitzar(s, t, increment);
		}
		return flow;
	}
}