package prop;

import java.util.*;
import java.math.*;
import java.io.*;

public class FordFulkerson extends Algorisme {
	Graf G;
	int[] cami;
	int INF = Integer.MAX_VALUE;
	
	public FordFulkerson(Graf GG) {
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
			List<Integer> L = G.adjacents(u);
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
			/*
			ArrayList<Integer> c = way(s, t);
			System.out.println(c.size());
			for(int i = 0; i < c.size(); ++i) {
				System.out.println(c.get(i));
			}
			*/
			

		}
		return flow;
	}
	/*
	public int[] MinCut(int s, int t) {
		maxFlow(s, t);
		Queue<Integer> sol = new LinkedList<Integer>();
		int total = 0;
		
		int m = G.getNumA();
		
		for (int i = 0; i < m; ++i) {
			Aresta a = G.getA(i);
			int u = a.getcap();
			int v = a.getw();
			if (cami[u] != - 1 && cami[v] == -1) {
				++total;
				sol.add(i);
			}
		}
		
		int[] ans = new int[2*total];
		
		for (int i = 0; i < 2*total; i += 2) {
			int e = (Integer) sol.remove();
			ans[i] = G.getA(e).getv();
			ans[i + 1] = G.getA(e).getw();
		}

		return ans;
	}
	*/
}