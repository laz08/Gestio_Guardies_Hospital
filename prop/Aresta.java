package prop;
public class Aresta {
	private int v, w;
	private int flow, capacitat;
	
	public Aresta(int vertexv, int vertexw, int f, Integer cap) {
		v = vertexv;
		w = vertexw;
		flow = f;
		if (cap.equals(null)) capacitat = cap;
		else capacitat = 0;
	}
	
	public Aresta(Aresta a) {
		v = a.v;
		w = a.w;
		flow = a.flow;
		capacitat = a.capacitat;
	}
	
	public int getv() {
		return v;
	}
	
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
	
	public int getflow() {
		return flow;
	}
	
	public void resetflow() {
		flow = 0;
	}
	
	public int get contrari(int ver) {
		if (ver == v) return w;
		else if (ver == w) return v;
	}
	
	public void addresflow(int vertex, int f) {
		if (vertex == v) flow -= f;
		else if (vertex == w) flow += f; 
	}
	
	public int capres(int vertex) {
		if (vertex == v) return capacitat-flow;
		else if (vertex == w) return flow;
	}
}