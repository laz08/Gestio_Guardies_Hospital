package prop;

import java.util.*;
import java.math.*;
import java.io.*;

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
		List<Integer> L = G.getAdjacents(s);
        Iterator<Integer> it = L.iterator();
		
		while (it.hasNext()) {
			int e = (Integer) it.next();
			Aresta a = G.getA(e);
			if (s == a.getw()) continue;
			int v = a.contrari(s);
			a.addresflow(v, a.capres(s));
			exces[v] = exces[v]+a.getflow();
			exces[s] = exces[s]-a.getflow();
			if (v != t) {
				esta[v] = true;
				q.add(v);
			}
		}
	}
	
	public void push(int u, int e) {
		Aresta a = G.getA(e);
		int aux = min(exces[u], a.capres(u));
		int v = a.contrari(u);
		a.addresflow(v, aux);
		exces[u] = exces[u]-aux;
		exces[v] = exces[v]+aux;
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
	@Override
	public int maxFlow(int s, int t) {
		
		int[] cami = new int[G.getNumA()];
		if (BFS(s, t, cami)) return ZERO;
		
		q = new LinkedList<Integer>();
		esta = new Boolean[G.getNumA()];
		preflow(s, t);
		
		while(!q.isEmpty()) {
			
            int u = (Integer) q.peek();
            List<Integer> L = G.getAdjacents(u);
            Iterator<Integer> it = L.iterator();
            int nou = -1;
            
            while (it.hasNext() && exces[u] > 0) {
            	int e = (Integer) it.next();
            	Aresta a = G.getA(e);
            	int v = a.contrari(u);
            	
            	if (a.capres(u) > 0) {
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